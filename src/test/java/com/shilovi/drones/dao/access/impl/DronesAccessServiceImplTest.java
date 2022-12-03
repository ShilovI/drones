package com.shilovi.drones.dao.access.impl;

import com.shilovi.drones.dao.access.DronesAccessService;
import com.shilovi.drones.dao.repository.DronesRepository;
import com.shilovi.drones.model.DroneDto;
import com.shilovi.drones.model.base.DroneModel;
import com.shilovi.drones.model.base.DroneState;
import com.shilovi.drones.utils.tag.SpringDatabaseTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.SqlConfig.TransactionMode.INFERRED;

@SpringDatabaseTest
@Sql(scripts = "classpath:sql/clear.sql", config = @SqlConfig(transactionMode = INFERRED), executionPhase = AFTER_TEST_METHOD)
class DronesAccessServiceImplTest {

    @Autowired
    private DronesAccessService accessService;

    @Test
    @Sql(scripts = "classpath:sql/drones/fill_content.sql", config = @SqlConfig(transactionMode = INFERRED))
    void findBySerialNumber() {
        DroneDto dto = accessService.findBySerialNumber("drone1");
        assertAll(() -> assertEquals("drone1", dto.getSerialNumber()),
                () -> assertEquals(300L, dto.getWeightLimit()),
                () -> assertEquals(DroneModel.MIDDLE, dto.getModel()),
                () -> assertEquals(DroneState.IDLE, dto.getState()));
    }

}