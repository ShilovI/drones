package com.shilovi.drones.dao.access;

import com.shilovi.drones.model.DroneDto;

import java.util.Collection;

public interface DronesAccessService {

    DroneDto save(DroneDto dto);

    DroneDto findBySerialNumber(String serialNumber);

    boolean doesDroneExists(String serialNumber);

    Collection<String> findAllSerialNumbers();
}
