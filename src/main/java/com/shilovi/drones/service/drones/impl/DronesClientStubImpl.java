package com.shilovi.drones.service.drones.impl;

import com.shilovi.drones.service.drones.DronesClient;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class DronesClientStubImpl implements DronesClient {

    /*It is not convenient to store the capacity in the database, because this characteristic is constantly changing.
    Inside this method, there should be a connection with the drone by its serial number.*/
    @Override
    public Integer getBatteryCapacity(String serialNumber) {
        return new Random().nextInt(101);
    }

}
