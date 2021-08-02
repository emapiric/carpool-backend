package com.carpool.config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.carpool.MyLoggerClass;
import com.carpool.dto.AuthenticationResponseDto;
import com.carpool.dto.UserDto;
import com.carpool.filter.JwtRequestFilter;
import com.carpool.service.impl.CustomOAuth2UserService;
import com.carpool.service.impl.GoogleLoginService;
import com.carpool.util.CustomOAuth2User;
import com.carpool.util.JwtUtil;
import com.google.gson.Gson;


@Configuration
@EnableWebSecurity
class WebSecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer{
	@Autowired
	private UserDetailsService myUserDetailsService;
	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private GoogleLoginService googleService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(myUserDetailsService);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Autowired
	private CustomOAuth2UserService oauthUserService;

	/*
	 * 
	 * For Google go to http://localhost:8080/oauth2/authorization/google
	 * 
	 * 
	 */

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf().disable().authorizeRequests()
				.antMatchers("/**")
				.permitAll().anyRequest().authenticated().and().formLogin().permitAll().and().exceptionHandling().and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().oauth2Login()
				.loginPage("/authenticate").userInfoEndpoint().userService(oauthUserService).and()
				.successHandler(new AuthenticationSuccessHandler() {

					@Override
					public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
							Authentication authentication) throws IOException, ServletException {

						CustomOAuth2User oauthUser = (CustomOAuth2User) authentication.getPrincipal();

						if (oauthUser == null) {
							response.setStatus(HttpStatus.NOT_FOUND.value());
							response.setContentType("application/json");
							response.setCharacterEncoding("UTF-8");
							return;
						}

						UserDto cd = null;

						String email = (String) oauthUser.getAttributes().get("email");
						String username = (String) oauthUser.getAttributes().get("username");

						try {
							cd = googleService.processOAuthPostLogin(email, username);
						} catch (org.springframework.dao.DataIntegrityViolationException greska) {
							// Klient vec postoji
							cd = googleService.processOAuthPostLogin(email, username);
						}

						if (cd == null) {
							// Greska
							response.setStatus(HttpStatus.NOT_FOUND.value());
							response.setContentType("application/json");
							response.setCharacterEncoding("UTF-8");
							return;
						}

						final String jwt = jwtTokenUtil.generateTokenFromString(cd.getEmail());

						String authJsonString = new Gson().toJson(new AuthenticationResponseDto(jwt, 1));
						response.setStatus(HttpStatus.OK.value());
						response.setContentType("application/json");
						response.setCharacterEncoding("UTF-8");

						PrintWriter out = response.getWriter();
						out.print(authJsonString);
						out.flush();

						MyLoggerClass.log.info("User " + oauthUser.getEmail()
								+ " has logged in successfully using his google account");

					}
				});

		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

	}
	
	@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8100")
                .allowedMethods("GET", "PUT", "POST", "DELETE");
    }

}
