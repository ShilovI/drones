package com.shilovi.drones.service.drones.impl;

import com.shilovi.drones.dao.access.DronesAccessService;
import com.shilovi.drones.exception.DroneUnavailableException;
import com.shilovi.drones.exception.NotFoundException;
import com.shilovi.drones.model.BatteryLevelDto;
import com.shilovi.drones.model.DroneDto;
import com.shilovi.drones.service.drones.DronesClient;
import com.shilovi.drones.service.drones.DronesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DronesServiceImpl implements DronesService {

    private final DronesAccessService service;
    private final DronesClient client;

    @Autowired
    public DronesServiceImpl(DronesAccessService service,
                             DronesClient client) {
        this.service = service;
        this.client = client;
    }

    @Override
    public DroneDto register(DroneDto dto) {
        return service.save(dto);
    }

    @Override
    public BatteryLevelDto checkBatteryLevel(String serialNumber) {
        if (service.doesDroneExists(serialNumber)) {
            try {
                return BatteryLevelDto.builder()
                        .serialNumber(serialNumber)
                        .batteryLevel(client.getBatteryCapacity(serialNumber))
                        .build();
            } catch (Exception e) {
                throw new DroneUnavailableException("Drone %s is unavailable!".formatted(serialNumber));
            }
        } else {
            throw new NotFoundException("Drone wasn't found by serial number : %s"
                    .formatted(serialNumber));
        }
    }

}
