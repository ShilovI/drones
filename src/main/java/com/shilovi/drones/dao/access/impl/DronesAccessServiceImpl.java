package com.shilovi.drones.dao.access.impl;

import com.shilovi.drones.dao.access.DronesAccessService;
import com.shilovi.drones.dao.repository.DronesRepository;
import com.shilovi.drones.exception.NotFoundException;
import com.shilovi.drones.model.DroneDto;
import com.shilovi.drones.utilities.mapper.DroneEntityToDroneDtoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public void save() {
        throw new UnsupportedOperationException("Implement me!");
    }

    @Override
    public DroneDto findBySerialNumber(String serialNumber) {
        return repository.findBySerialNumber(serialNumber)
                .map(DroneEntityToDroneDtoMapper::map)
                .orElseThrow(() -> new NotFoundException("Drone wasn't found by serial number : %s"
                        .formatted(serialNumber)));
    }
}
