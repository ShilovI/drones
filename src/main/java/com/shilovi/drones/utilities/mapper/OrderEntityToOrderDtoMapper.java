package com.shilovi.drones.utilities.mapper;

import com.shilovi.drones.dao.entity.MedicationEntity;
import com.shilovi.drones.dao.entity.OrderEntity;
import com.shilovi.drones.model.OrderDto;
import lombok.experimental.UtilityClass;

import java.util.stream.Collectors;

@UtilityClass
public class OrderEntityToOrderDtoMapper {

    public OrderDto map(OrderEntity source) {
        return OrderDto.builder()
                .orderId(source.getId())
                .medication(source.getMedicationEntities().stream()
                        .map(MedicationEntity::getCode)
                        .collect(Collectors.toList()))
                .build();
    }

}
