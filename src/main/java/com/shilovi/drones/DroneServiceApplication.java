package com.shilovi.drones;

import com.shilovi.drones.config.properties.DroneProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties(
        value = {
                DroneProperties.class,
        }
)
public class DroneServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DroneServiceApplication.class, args);
    }

}
