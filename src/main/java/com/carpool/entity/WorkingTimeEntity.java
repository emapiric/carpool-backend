package com.carpool.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "working_time")
public class WorkingTimeEntity implements MyEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "day_of_week")
	private int dayOfWeek;
	@Column(name = "start_time")
	private LocalDate startTime = LocalDate.now();
	@Column(name = "end_time")
	private LocalDate endTime = LocalDate.now();
	@ManyToOne
	@JoinColumn(name = "userId", referencedColumnName = "id")
	private UserEntity user;

	public WorkingTimeEntity(Long id, int dayOfWeek, LocalDate startTime, LocalDate endTime, UserEntity user) {
		super();
		this.id = id;
		this.dayOfWeek = dayOfWeek;
		this.startTime = startTime;
		this.endTime = endTime;
		this.user = user;
	}

	public WorkingTimeEntity() {
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

	public LocalDate getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDate startTime) {
		this.startTime = startTime;
	}

	public LocalDate getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDate endTime) {
		this.endTime = endTime;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "WorkingTimeEntity{" + "id=" + id + ", dayOfWeek=" + dayOfWeek + ", startTime=" + startTime
				+ ", endTime=" + endTime + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof WorkingTimeEntity))
			return false;
		WorkingTimeEntity that = (WorkingTimeEntity) o;
		return id == that.id && dayOfWeek == that.dayOfWeek && Objects.equals(startTime, that.startTime)
				&& Objects.equals(endTime, that.endTime);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, dayOfWeek, startTime, endTime);
	}
}
