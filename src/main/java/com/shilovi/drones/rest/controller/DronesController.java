package com.shilovi.drones.rest.controller;

import com.shilovi.drones.model.BatteryLevelDto;
import com.shilovi.drones.model.DroneDto;
import com.shilovi.drones.service.drones.DronesService;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
@RequestMapping("/drones")
@ConditionalOnProperty(prefix = "application.rest.controller", name = "drones", havingValue = "enabled", matchIfMissing = true)
public class DronesController {

    private final DronesService service;

    @Autowired
    DronesController(DronesService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(OK)
    public DroneDto register(@RequestBody @Validated DroneDto request) {
        logger.info("Drone register request={}", request);
        return service.register(request);
    }

    @GetMapping("/batteryLevel/{serialNumber}")
    @ResponseStatus(OK)
    public BatteryLevelDto check(@PathVariable("serialNumber") @NotNull String serialNumber) {
        logger.info("Check battery level for drone = {}", serialNumber);
        return service.checkBatteryLevel(serialNumber);
    }

}
