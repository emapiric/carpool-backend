package com.carpool.controller.rest;

import com.carpool.dto.CarpoolDto;
import com.carpool.dto.CarpoolRequestDto;
import com.carpool.dto.RideDto;
import com.carpool.dto.RideRequestDto;
import com.carpool.dto.UserDto;
import com.carpool.service.CarpoolService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/carpool")
public class CarpoolController {

    CarpoolService carpoolService;

    @PostMapping("create")
    public ResponseEntity<Object> createCarpool(@RequestBody List<CarpoolDto> carpoolDtoList) {
        try {
            carpoolService.createCarpool(carpoolDtoList);
            return ResponseEntity.status(HttpStatus.OK).body("Carpool successfully created");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @RequestMapping("search")
    public ResponseEntity<List<CarpoolDto>> search(CarpoolRequestDto carpoolRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(carpoolService.search(carpoolRequestDto));
    }
}
