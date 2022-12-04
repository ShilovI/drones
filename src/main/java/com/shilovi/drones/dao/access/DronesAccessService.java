package com.shilovi.drones.dao.access;

import com.shilovi.drones.model.DroneDto;
import com.shilovi.drones.model.OrderDto;

import java.util.Collection;

public interface DronesAccessService {

    DroneDto save(DroneDto dto);

    DroneDto findBySerialNumber(String serialNumber);

    boolean doesDroneExists(String serialNumber);

    void assignAnOrder(Long orderId, String serialNumber);

    OrderDto getOrderByDrone(String serialNumber);

    Collection<DroneDto> getAllFreeDrones();

    Collection<String> findAllSerialNumbers();
}
