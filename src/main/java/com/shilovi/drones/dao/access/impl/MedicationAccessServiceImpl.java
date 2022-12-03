package com.shilovi.drones.dao.access.impl;

import com.shilovi.drones.dao.access.MedicationAccessService;
import com.shilovi.drones.dao.repository.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MedicationAccessServiceImpl implements MedicationAccessService {

    private final MedicationRepository repository;

    @Autowired
    public MedicationAccessServiceImpl(MedicationRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save() {
        throw new UnsupportedOperationException("Implement me!");
    }
}
