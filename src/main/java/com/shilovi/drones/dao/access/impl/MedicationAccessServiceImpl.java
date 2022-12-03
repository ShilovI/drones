package com.shilovi.drones.dao.access.impl;

import com.shilovi.drones.dao.access.MedicationAccessService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MedicationAccessServiceImpl implements MedicationAccessService {

    @Override
    public void save() {
        throw new UnsupportedOperationException("Implement me!");
    }
}
