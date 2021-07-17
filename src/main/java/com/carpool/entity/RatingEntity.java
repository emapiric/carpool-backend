package com.carpool.entity;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name="rating")
public class RatingEntity implements MyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int rating;
    private LocalDate date = LocalDate.now();
    private String comment;
    @ManyToOne
    @JoinColumn(name="driver", referencedColumnName = "id")
    private UserEntity driver;

    public RatingEntity(Long id, int rating, LocalDate date, String comment, UserEntity driver) {
        this.id = id;
        this.rating = rating;
        this.date = date;
        this.comment = comment;
        this.driver = driver;
    }

    public RatingEntity() {
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

    public UserEntity getDriver() {
        return driver;
    }

    public void setDriver(UserEntity driver) {
        this.driver = driver;
    }

    @Override
    public String toString() {
        return "RatingEntity{" +
                "id=" + id +
                ", rating=" + rating +
                ", date=" + date +
                ", comment='" + comment + '\'' +
                ", driver=" + driver +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RatingEntity)) return false;
        RatingEntity that = (RatingEntity) o;
        return rating == that.rating && Objects.equals(id, that.id) && Objects.equals(date, that.date) && Objects.equals(comment, that.comment) && Objects.equals(driver, that.driver);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rating, date, comment, driver);
    }
}
