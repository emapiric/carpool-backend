package com.carpool.service;

import com.carpool.dto.CarpoolDto;
import com.carpool.dto.CarpoolRequestDto;

import java.util.List;

public interface CarpoolService {
    void createCarpool(List<CarpoolDto> carpoolDtoList) throws Exception;

    List<CarpoolDto> search(CarpoolRequestDto carpoolRequestDto);
}
