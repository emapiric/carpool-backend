package com.carpool.service.impl;

import static java.time.temporal.ChronoUnit.MINUTES;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carpool.constants.WorkDay;
import com.carpool.dto.CarpoolDto;
import com.carpool.dto.CarpoolRequestDto;
import com.carpool.dto.RideRequestDto;
import com.carpool.dto.WorkingTimeDto;
import com.carpool.entity.CarpoolEntity;
import com.carpool.entity.WorkingTimeEntity;
import com.carpool.mapper.CarpoolEntityDtoMapper;
import com.carpool.repository.CarpoolRepository;
import com.carpool.service.CarpoolService;
import com.carpool.service.HaversineService;

@Service
@Transactional
public class CarpoolServiceImpl implements CarpoolService {

    private CarpoolRepository carpoolRepository;
    private CarpoolEntityDtoMapper carpoolMapper;
    private HaversineService haversineService;

    @Autowired
    public CarpoolServiceImpl(CarpoolRepository carpoolRepository, CarpoolEntityDtoMapper carpoolMapper, HaversineService haversineService) {
        this.carpoolRepository = carpoolRepository;
        this.carpoolMapper = carpoolMapper;
        this.haversineService = haversineService;
    }

    @Override
    public void createCarpool(List<CarpoolDto> carpoolDtoList) throws Exception {
        List<CarpoolEntity> carpoolEntityList = carpoolDtoList.stream().map(dto -> {
            return carpoolMapper.toEntity(dto);
        })
                .collect(Collectors.toList());
        carpoolRepository.saveAll(carpoolEntityList);
    }

    @Override
    public List<CarpoolDto> search(CarpoolRequestDto carpoolRequestDto) {
        return carpoolRepository
                .findAll()
                .stream().filter(carpool ->
                        haversineService.isOnWay(
                                carpool.getRideTo(),
                                new RideRequestDto(carpoolRequestDto.getHomeAddress(), carpoolRequestDto.getWorkAddress())
                        ) &&
                                isInTimeframe(carpool.getRideTo().getDriver().getWorkDays(), carpoolRequestDto.getWorkingTimeList()) &&
                                carpool.getRideTo().hasSpace()
                )
                .map(carpool -> carpoolMapper.toDto(carpool)).collect(Collectors.toList());
    }

    private boolean isInTimeframe(List<WorkingTimeEntity> driversDays, List<WorkingTimeDto> usersDays) {
        int incompatibleDays = 0;
        for (WorkDay workDay : WorkDay.values()) {
            if (incompatibleDays > 3) return false;
            try {
                WorkingTimeEntity driverWorkingTime = driversDays.stream().filter(day -> day.getDayOfWeek().equals(workDay)).collect(Collectors.toList()).get(0);
                WorkingTimeDto userWorkingTime = usersDays.stream().filter(day -> day.getDayOfWeek().equals(workDay)).collect(Collectors.toList()).get(0);
                if(MINUTES.between(driverWorkingTime.getStartTime(), userWorkingTime.getStartTime()) > 30 ||
                        MINUTES.between(driverWorkingTime.getEndTime(), userWorkingTime.getEndTime()) > 30) {
                    incompatibleDays++;
                }
            } catch(Exception e) {
                incompatibleDays++;
            }
        }
        return true;
    }

}
