package com.shilovi.drones.dao.access.impl;

import com.shilovi.drones.dao.access.DronesAccessService;
import com.shilovi.drones.dao.repository.DronesRepository;
import com.shilovi.drones.exception.NotFoundException;
import com.shilovi.drones.model.DroneDto;
import com.shilovi.drones.utilities.mapper.DroneDtoToDroneEntityMapper;
import com.shilovi.drones.utilities.mapper.DroneEntityToDroneDtoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Slf4j
@Service
@Transactional
public class DronesAccessServiceImpl implements DronesAccessService {

    private final DronesRepository repository;

    @Autowired
    DronesAccessServiceImpl(DronesRepository repository) {
        this.repository = repository;
    }

    @Override
    public DroneDto save(DroneDto dto) {
        return DroneEntityToDroneDtoMapper.map(repository.save(DroneDtoToDroneEntityMapper.map(dto)));
    }

    @Override
    public DroneDto findBySerialNumber(String serialNumber) {
        return repository.findBySerialNumber(serialNumber)
                .map(DroneEntityToDroneDtoMapper::map)
                .orElseThrow(() -> new NotFoundException("Drone wasn't found by serial number : %s"
                        .formatted(serialNumber)));
    }

    @Override
    public boolean doesDroneExists(String serialNumber) {
        return repository.doesExist(serialNumber);
    }

    @Override
    public Collection<String> findAllSerialNumbers() {
        return repository.findAllSerialNumbers();
    }

}
