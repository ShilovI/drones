package com.shilovi.drones.dao.access.impl;

import com.shilovi.drones.dao.access.MedicationAccessService;
import com.shilovi.drones.dao.entity.MedicationEntity;
import com.shilovi.drones.dao.repository.MedicationRepository;
import com.shilovi.drones.exception.BrokenFileException;
import com.shilovi.drones.exception.NotFoundException;
import com.shilovi.drones.model.MedicationDto;
import com.shilovi.drones.utilities.mapper.MedicationDtoToMedicationEntityMapper;
import com.shilovi.drones.utilities.mapper.MedicationEntityToMedicationDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Transactional
public class MedicationAccessServiceImpl implements MedicationAccessService {

    private final MedicationRepository repository;

    @Autowired
    public MedicationAccessServiceImpl(MedicationRepository repository) {
        this.repository = repository;
    }

    @Override
    public MedicationDto save(MedicationDto dto) {
        return MedicationEntityToMedicationDtoMapper.map(
                repository.save(MedicationDtoToMedicationEntityMapper.map(dto))
        );
    }

    @Override
    public void uploadFile(String code, MultipartFile file) {
        MedicationEntity entity = repository.findByCode(code)
                .orElseThrow(() -> new NotFoundException("Medication not found by : %s".formatted(code)));
        try {
            entity.setImage(file.getBytes());
        } catch (IOException e) {
            throw new BrokenFileException("Broken file!");
        }
        repository.save(entity);
    }

}
