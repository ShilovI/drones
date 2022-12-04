package com.shilovi.drones.service.drones;

import com.shilovi.drones.model.BatteryLevelDto;
import com.shilovi.drones.model.DroneDto;

public interface DronesService {

    DroneDto register(DroneDto dto);

    BatteryLevelDto checkBatteryLevel(String serialNumber);

}
