package com.springboot.parkinglot.serviceimpl;

import com.springboot.parkinglot.model.VehicleDTO;
import com.springboot.parkinglot.repository.ParkingRepository;
import com.springboot.parkinglot.service.ParkingService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkingServiceImpl implements ParkingService {

    @Autowired
    ParkingRepository parkingRepository;

    @Override
    public List<VehicleDTO> getAllVehicles() {
        return parkingRepository.findAll();
    }

    private boolean isContains(VehicleDTO vehicleDTO) {
        return getAllVehicles().stream().anyMatch(vehicle -> vehicle.getId().equals(vehicleDTO.getId()));
        }

    @Override
    public String park(VehicleDTO vehicleDTO) {
        if (vehicleDTO.getId() > 0 && vehicleDTO.getId() <= 10) {
            if (!isContains(vehicleDTO)) {
                parkingRepository.save(vehicleDTO);
            } else {
                throw new RuntimeException(vehicleDTO.getId() + " slot is vacant!");
            }
        } else {
            throw new RuntimeException("Provide id between 1 to 10");
        }
        return "Parked!";
    }

    @Override
    public String unPark(Long id) {
        if (id > 0 && id <= 10) {
            parkingRepository.deleteById(id);
        } else {
            throw new RuntimeException("Provide id between 1 to 10");
        }
        return "Unparked vehicle at slot number " + id;
    }
}
