package com.shilovi.drones.model.base;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.EnumSet;
import java.util.Objects;

@Getter
@RequiredArgsConstructor
public enum DroneModel {

    LIGHT("Lightweight"),
    MIDDLE("Middleweight"),
    CRUISER("Cruiserweight"),
    HEAVY("Heavyweight");

    private final String model;

    public static DroneModel modelOf(String model) {
        return EnumSet.allOf(DroneModel.class)
                .stream()
                .filter(it -> Objects.equals(it.model, model))
                .findFirst()
                .orElseThrow();
    }

    @Override
    public String toString() {
        return String.valueOf(model);
    }

}

