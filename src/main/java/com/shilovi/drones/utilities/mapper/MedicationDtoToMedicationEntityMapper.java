package com.shilovi.drones.utilities.mapper;

import com.shilovi.drones.dao.entity.MedicationEntity;
import com.shilovi.drones.model.MedicationDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MedicationDtoToMedicationEntityMapper {

    public MedicationEntity map(MedicationDto source) {
        return MedicationEntity.builder()
                .code(source.getCode())
                .name(source.getName())
                .weight(source.getWeight())
                .build();
    }

}
