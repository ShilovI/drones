package com.shilovi.drones.service.drones.impl;

import com.shilovi.drones.config.properties.DroneProperties;
import com.shilovi.drones.dao.access.DronesAccessService;
import com.shilovi.drones.exception.DroneUnavailableException;
import com.shilovi.drones.exception.NotFoundException;
import com.shilovi.drones.model.BatteryLevelDto;
import com.shilovi.drones.model.DroneCollectionDto;
import com.shilovi.drones.model.DroneDto;
import com.shilovi.drones.model.OrderDto;
import com.shilovi.drones.service.drones.DronesClient;
import com.shilovi.drones.service.drones.DronesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DronesServiceImpl implements DronesService {

    private final DronesAccessService service;
    private final DronesClient client;
    private final DroneProperties properties;

    @Autowired
    public DronesServiceImpl(DronesAccessService service,
                             DronesClient client,
                             DroneProperties properties) {
        this.service = service;
        this.client = client;
        this.properties = properties;
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
                        .batteryLevel(client.getBatteryLevel(serialNumber))
                        .build();
            } catch (Exception e) {
                throw new DroneUnavailableException("Drone %s is unavailable!".formatted(serialNumber));
            }
        } else {
            throw new NotFoundException("Drone wasn't found by serial number : %s"
                    .formatted(serialNumber));
        }
    }

    @Override
    public void assignAnOrder(Long orderId, String serialNumber) {
        /*here and in method which will be called we can use some kind of fabric with check instead of if statement
        fabric can store checks line - drone charged enough, drone can lift orders weight, drone free, order exist etc.*/

        if(properties.getMinBatteryLevelForDelivering() <
           //here can be exception if drone doesn't exist!
           client.getBatteryLevel(serialNumber)) {
            service.assignAnOrder(orderId, serialNumber);
        } else {
            throw new UnsupportedOperationException("Drone %s isn't charged enough!".formatted(serialNumber));
        }
    }

    @Override
    public OrderDto checkOrder(String serialNumber) {
        return service.getOrderByDrone(serialNumber);
    }

    @Override
    public DroneCollectionDto getAllFreeDrones() {
        return DroneCollectionDto.builder()
                .drones(service.getAllFreeDrones())
                .build();
    }

}
