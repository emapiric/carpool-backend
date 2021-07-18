package com.carpool.dto;


import java.time.LocalDate;
import java.util.Objects;

public class SimpleRatingDto implements MyDto{

    private static final long serialVersionUID = 1L;
    private Long id;
    private int rating;
    private LocalDate date = LocalDate.now();
    private String comment;

    public SimpleRatingDto(Long id, int rating, LocalDate date, String comment) {
        this.id = id;
        this.rating = rating;
        this.date = date;
        this.comment = comment;
    }

    public SimpleRatingDto() {
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "RatingDto{" +
                "id=" + id +
                ", rating=" + rating +
                ", date=" + date +
                ", comment='" + comment + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SimpleRatingDto)) return false;
        SimpleRatingDto ratingDto = (SimpleRatingDto) o;
        return rating == ratingDto.rating && Objects.equals(id, ratingDto.id) && Objects.equals(date, ratingDto.date) && Objects.equals(comment, ratingDto.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rating, date, comment);
    }
}
