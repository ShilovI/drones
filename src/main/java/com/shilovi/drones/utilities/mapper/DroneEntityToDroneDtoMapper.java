package com.shilovi.drones.utilities.mapper;

import com.shilovi.drones.dao.entity.DroneEntity;
import com.shilovi.drones.model.DroneDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DroneEntityToDroneDtoMapper {

    public DroneDto map(DroneEntity source) {
        return DroneDto.builder()
                .serialNumber(source.getSerialNumber())
                .weightLimit(source.getWeightLimit())
                .model(source.getModel().getModel())
                .state(source.getState().getState())
                .build();
    }

}
