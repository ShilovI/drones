package com.shilovi.drones.model.base.conveter;

import com.shilovi.drones.model.base.ActiveType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ActiveTypeConverter implements AttributeConverter<ActiveType, Boolean> {

    @Override
    public Boolean convertToDatabaseColumn(ActiveType type) {
        return type.isActive();
    }

    @Override
    public ActiveType convertToEntityAttribute(Boolean active) {
        return ActiveType.activeOf(active);
    }
}