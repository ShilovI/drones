package com.shilovi.drones.service.drones;

import com.shilovi.drones.model.BatteryLevelDto;
import com.shilovi.drones.model.DroneCollectionDto;
import com.shilovi.drones.model.DroneDto;
import com.shilovi.drones.model.OrderDto;

public interface DronesService {

    DroneDto register(DroneDto dto);

    BatteryLevelDto checkBatteryLevel(String serialNumber);

    void assignAnOrder(Long orderId, String serialNumber);

    OrderDto checkOrder(String serialNumber);

    DroneCollectionDto getAllFreeDrones();

}
