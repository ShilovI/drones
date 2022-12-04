package com.shilovi.drones.exception;

import lombok.Getter;

@Getter
public class DroneUnavailableException extends RuntimeException {

    public DroneUnavailableException(String message) {
        super(message);
    }
}
