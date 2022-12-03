package com.shilovi.drones.dao.access.impl;

import com.shilovi.drones.dao.access.DronesAccessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class DronesAccessServiceImpl implements DronesAccessService {

    @Override
    public void save() {
        throw new UnsupportedOperationException("Implement me!");
    }
}
