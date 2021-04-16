package com.springboot.parkinglot.controller;

import com.springboot.parkinglot.model.VehicleDTO;
import com.springboot.parkinglot.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parkinglot")
public class ParkingLotController {

    @Autowired
    ParkingService parkingService;

    @GetMapping("/parkinginfo")
    public List<VehicleDTO> getAllVehicles() {
        return parkingService.getAllVehicles();
    }

    @PostMapping("/park")
    public String park(VehicleDTO vehicleDTO) {
        return parkingService.park(vehicleDTO);
    }

    @DeleteMapping("/unPark")
    public String unPark(Long id) {
        return parkingService.unPark(id);
    }

}
