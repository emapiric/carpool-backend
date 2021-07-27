package com.carpool.dto;

import com.carpool.constants.WorkDay;

import java.time.LocalTime;
import java.util.Objects;

public class WorkingTimeDto implements MyDto{

    private static final long serialVersionUID = 1L;
    private Long id;
    private WorkDay dayOfWeek;
    private LocalTime startTime = LocalTime.now();
    private LocalTime endTime= LocalTime.now();

    public WorkingTimeDto(Long id, WorkDay dayOfWeek, LocalTime startTime, LocalTime endTime) {
        this.id = id;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public WorkingTimeDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WorkDay getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(WorkDay dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "WorkingTimeDto{" +
                "id=" + id +
                ", dayOfWeek=" + dayOfWeek +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WorkingTimeDto)) return false;
        WorkingTimeDto that = (WorkingTimeDto) o;
        return dayOfWeek == that.dayOfWeek && Objects.equals(id, that.id) && Objects.equals(startTime, that.startTime) && Objects.equals(endTime, that.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dayOfWeek, startTime, endTime);
    }
}
