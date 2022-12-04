package com.shilovi.drones.service.scheduller;

import com.shilovi.drones.dao.access.DronesAccessService;
import com.shilovi.drones.service.drones.DronesClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Scheduler {

    private final DronesAccessService service;
    private final DronesClient client;

    @Autowired
    public Scheduler(DronesAccessService service,
                     DronesClient client) {
        this.service = service;
        this.client = client;
    }


    @Scheduled(cron = "${application.scheduler.battery-capacities.cron}")
    public void process() {
        //it can also be stored in database
        service.findAllSerialNumbers()
                .forEach(serialNumber -> logger.info("Battery level for drone {} is {}", serialNumber,
                        client.getBatteryLevel(serialNumber)));
    }

}
