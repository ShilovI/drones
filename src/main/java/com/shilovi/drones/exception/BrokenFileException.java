package com.shilovi.drones.exception;

import lombok.Getter;

@Getter
public class BrokenFileException extends RuntimeException {

    public BrokenFileException(String message) {
        super(message);
    }
}
