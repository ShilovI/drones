package com.shilovi.drones.utilities.mapper;

import com.shilovi.drones.dao.entity.DroneEntity;
import com.shilovi.drones.model.DroneDto;
import com.shilovi.drones.model.base.DroneModel;
import com.shilovi.drones.model.base.DroneState;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DroneDtoToDroneEntityMapper {

    public DroneEntity map(DroneDto source) {
        return DroneEntity.builder()
                .serialNumber(source.getSerialNumber())
                .weightLimit(source.getWeightLimit())
                .model(DroneModel.modelOf(source.getModel()))
                .state(DroneState.stateOf(source.getState()))
                .build();
    }

}
