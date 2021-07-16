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
@Table(name="working_time")
public class WorkingTimeEntity implements MyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="day_of_week")
    private int dayOfWeek;
    @Column(name="start_time")
    private LocalDate startTime = LocalDate.now();
    @Column(name="end_time")
    private LocalDate endTime = LocalDate.now();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WorkingTimeEntity)) return false;
        WorkingTimeEntity that = (WorkingTimeEntity) o;
        return id == that.id && dayOfWeek == that.dayOfWeek && Objects.equals(startTime, that.startTime) && Objects.equals(endTime, that.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dayOfWeek, startTime, endTime);
    }
}
