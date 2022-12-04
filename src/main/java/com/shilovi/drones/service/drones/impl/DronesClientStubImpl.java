package com.shilovi.drones.service.drones.impl;

import com.shilovi.drones.service.drones.DronesClient;

import java.util.Random;

public class DronesClientStubImpl implements DronesClient {

    /*It is not convenient to store the capacity in the database, because this characteristic is constantly changing.
    Inside this method, there should be a connection with the drone by its serial number.*/
    @Override
    public int getBatteryCapacity(Long droneId) {
        Random r = new Random();
        return r.nextInt(101);
    }

}
