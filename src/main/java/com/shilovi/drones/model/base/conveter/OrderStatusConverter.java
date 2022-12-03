package com.shilovi.drones.model.base.conveter;

import com.shilovi.drones.model.base.OrderStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Optional;

@Converter(autoApply = true)
public class OrderStatusConverter implements AttributeConverter<OrderStatus, String> {

    @Override
    public String convertToDatabaseColumn(OrderStatus status) {
        return Optional.ofNullable(status).map(OrderStatus::getStatus).orElse(null);
    }

    @Override
    public OrderStatus convertToEntityAttribute(String status) {
        return OrderStatus.statusOf(status);
    }
}