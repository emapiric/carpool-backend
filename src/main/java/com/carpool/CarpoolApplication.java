package com.carpool;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CarpoolApplication {

	public static void main(String[] args) {
		try {
			File f = new File("./logs/spring.log");
			if (f.delete()) {
			} else {
			}
		} catch (Exception e) {
			System.out.println("Error while trying to delete log file!");
			e.printStackTrace();
			return;
		}
		SpringApplication.run(CarpoolApplication.class, args);
	}

}
