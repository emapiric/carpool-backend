package com.carpool.dto;

import java.util.List;
import java.util.Objects;

public class CarpoolRequestDto implements MyDto{
    private AddressRequestDto homeAddress;
    private AddressRequestDto workAddress;
    private List<WorkingTimeDto> workingTimeList;

    public CarpoolRequestDto(AddressRequestDto homeAddress, AddressRequestDto workAddress, List<WorkingTimeDto> workingTimeList) {
        this.homeAddress = homeAddress;
        this.workAddress = workAddress;
        this.workingTimeList = workingTimeList;
    }

    public CarpoolRequestDto() {
    }

    public AddressRequestDto getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(AddressRequestDto homeAddress) {
        this.homeAddress = homeAddress;
    }

    public AddressRequestDto getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(AddressRequestDto workAddress) {
        this.workAddress = workAddress;
    }

    public List<WorkingTimeDto> getWorkingTimeList() {
        return workingTimeList;
    }

    public void setWorkingTimeList(List<WorkingTimeDto> workingTimeList) {
        this.workingTimeList = workingTimeList;
    }

    @Override
    public String toString() {
        return "CarpoolRequestDto{" +
                "homeAddress=" + homeAddress +
                ", workAddress=" + workAddress +
                ", workingTimeList=" + workingTimeList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarpoolRequestDto that = (CarpoolRequestDto) o;
        return Objects.equals(homeAddress, that.homeAddress) && Objects.equals(workAddress, that.workAddress) && Objects.equals(workingTimeList, that.workingTimeList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homeAddress, workAddress, workingTimeList);
    }
}
