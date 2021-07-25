package com.carpool.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class WorkingTimeDto implements MyDto{

    private static final long serialVersionUID = 1L;
    private Long id;
    private int dayOfWeek;
    private LocalDateTime startTime = LocalDateTime.now();
    private LocalDateTime endTime= LocalDateTime.now();

    public WorkingTimeDto(Long id, int dayOfWeek, LocalDateTime startTime, LocalDateTime endTime) {
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

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
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
