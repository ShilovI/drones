package com.shilovi.drones.dao.access;

import com.shilovi.drones.model.DroneDto;

public interface DronesAccessService {

    void save();

    DroneDto findBySerialNumber(String serialNumber);
}
