package com.shilovi.drones.config.properties;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Validated
@Getter
@Setter
@ConfigurationProperties(prefix = "application.drone")
public class DroneProperties {

    @NotNull
    private Long minBatteryLevelForDelivering;

    @NotNull
    private Long maximumCarryWeight;

}