package com.carpool.dto;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;

import com.sun.istack.NotNull;

public class RatingDto implements MyDto{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	@NotNull
	@Positive
	@Max(5)
    private int rating;
    private LocalDateTime date = LocalDateTime.now();
    private String comment;
	@NotNull
    private SimpleUserDto driver;
    public RatingDto() {
		// TODO Auto-generated constructor stub
	}
	public RatingDto(Long id, int rating, LocalDateTime date, String comment, SimpleUserDto driver) {
		super();
		this.id = id;
		this.rating = rating;
		this.date = date;
		this.comment = comment;
		this.driver = driver;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public SimpleUserDto getDriver() {
		return driver;
	}
	public void setDriver(SimpleUserDto driver) {
		this.driver = driver;
	}
	@Override
	public int hashCode() {
		return Objects.hash(comment, date, driver, id, rating);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RatingDto other = (RatingDto) obj;
		return Objects.equals(comment, other.comment) && Objects.equals(date, other.date)
				&& Objects.equals(driver, other.driver) && Objects.equals(id, other.id) && rating == other.rating;
	}
    
    
}
