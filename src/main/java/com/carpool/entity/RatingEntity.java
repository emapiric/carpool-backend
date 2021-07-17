package com.carpool.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name="rating")
public class RatingEntity implements MyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int rating;
    private LocalDate date = LocalDate.now();
    private String comment;
    @ManyToOne
    @JoinColumn(name="id")
    private UserEntity driver;

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
