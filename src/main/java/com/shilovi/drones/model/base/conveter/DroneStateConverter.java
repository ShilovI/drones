package com.shilovi.drones.model.base.conveter;

import com.shilovi.drones.model.base.DroneState;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Optional;

@Converter(autoApply = true)
public class DroneStateConverter implements AttributeConverter<DroneState, String> {

    @Override
    public String convertToDatabaseColumn(DroneState state) {
        return Optional.ofNullable(state).map(DroneState::getState).orElse(null);
    }

    @Override
    public DroneState convertToEntityAttribute(String state) {
        return DroneState.stateOf(state);
    }
}