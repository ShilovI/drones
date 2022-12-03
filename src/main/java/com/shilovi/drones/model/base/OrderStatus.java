package com.shilovi.drones.model.base;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.EnumSet;
import java.util.Locale;
import java.util.Objects;

@Getter
@RequiredArgsConstructor
public enum OrderStatus {

    CREATING("CREATING"),
    CREATED("CREATED"),
    DELIVERING("DELIVERING"),
    DELIVERED("DELIVERED");

    private final String status;

    public static OrderStatus statusOf(String status) {
        return EnumSet.allOf(OrderStatus.class)
                .stream()
                .filter(it -> Objects.equals(it.status, status))
                .findFirst()
                .orElseThrow();
    }

    @Override
    public String toString() {
        return String.valueOf(status);
    }

}

