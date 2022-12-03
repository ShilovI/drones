package com.shilovi.drones.model.base.conveter;

import com.shilovi.drones.model.base.DroneModel;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Optional;

@Converter(autoApply = true)
public class DroneModelConverter implements AttributeConverter<DroneModel, String> {

    @Override
    public String convertToDatabaseColumn(DroneModel model) {
        return Optional.ofNullable(model).map(DroneModel::getModel).orElse(null);
    }

    @Override
    public DroneModel convertToEntityAttribute(String model) {
        return DroneModel.modelOf(model);
    }
}