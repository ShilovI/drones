package com.shilovi.drones.utilities.mapper;

import com.shilovi.drones.dao.entity.MedicationEntity;
import com.shilovi.drones.model.MedicationDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MedicationEntityToMedicationDtoMapper {

    public MedicationDto map(MedicationEntity source) {
        return MedicationDto.builder()
                .code(source.getCode())
                .name(source.getName())
                .weight(source.getWeight())
                .build();
    }

}
