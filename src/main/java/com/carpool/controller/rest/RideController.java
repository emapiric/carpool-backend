package com.carpool.controller.rest;

import com.carpool.dto.MyDto;
import com.carpool.dto.RideDto;
import com.carpool.service.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ride")
public class RideController implements MyDto {

    private static final long serialVersionUID = 1L;
    RideService rideService;

    @Autowired
    public RideController(RideService rideService) {
        this.rideService = rideService;
    }

    @GetMapping("/search")
    public ResponseEntity<List<RideDto>> search() throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(rideService.search());
    }
}
