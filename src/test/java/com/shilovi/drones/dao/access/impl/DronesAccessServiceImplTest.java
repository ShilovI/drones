package com.shilovi.drones.dao.access.impl;

import com.shilovi.drones.dao.access.DronesAccessService;
import com.shilovi.drones.model.DroneDto;
import com.shilovi.drones.model.base.DroneModel;
import com.shilovi.drones.model.base.DroneState;
import com.shilovi.drones.utils.tag.SpringDatabaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.SqlConfig.TransactionMode.INFERRED;

@SpringDatabaseTest
class DronesAccessServiceImplTest {

    @Autowired
    private DronesAccessService accessService;

    @Test
    @Sql(scripts = "classpath:sql/drones/fill_drone.sql", config = @SqlConfig(transactionMode = INFERRED))
    @Sql(scripts = "classpath:sql/clear.sql", config = @SqlConfig(transactionMode = INFERRED), executionPhase = AFTER_TEST_METHOD)
    void findBySerialNumber() {
        DroneDto dto = accessService.findBySerialNumber("drone1");
        assertAll(() -> assertEquals("drone1", dto.getSerialNumber()),
                () -> assertEquals(300L, dto.getWeightLimit()),
                () -> assertEquals(DroneModel.MIDDLE.getModel(), dto.getModel()),
                () -> assertEquals(DroneState.IDLE.getState(), dto.getState()));
    }

    @Test
    @Sql(scripts = "classpath:sql/drones/fill_drone.sql", config = @SqlConfig(transactionMode = INFERRED))
    @Sql(scripts = "classpath:sql/clear.sql", config = @SqlConfig(transactionMode = INFERRED), executionPhase = AFTER_TEST_METHOD)
    void doesDroneExistsTrue() {
        assertTrue(accessService.doesDroneExists("drone1"));
    }

    @Test
    @Sql(scripts = {"classpath:sql/drones/fill_drone.sql"},
            config = @SqlConfig(transactionMode = INFERRED))
    @Sql(scripts = "classpath:sql/clear.sql", config = @SqlConfig(transactionMode = INFERRED), executionPhase = AFTER_TEST_METHOD)
    void doesDroneExistsFalse() {
        assertFalse(accessService.doesDroneExists("drone2"));
    }

    @Test
    @Sql(scripts = {"classpath:sql/drones/fill_drone.sql", "classpath:sql/drones/fill_drone2.sql"},
            config = @SqlConfig(transactionMode = INFERRED))
    @Sql(scripts = "classpath:sql/clear.sql", config = @SqlConfig(transactionMode = INFERRED), executionPhase = AFTER_TEST_METHOD)
    void doesDroneExists() {
        Collection<String> numbers = accessService.findAllSerialNumbers();
        assertAll(() -> assertEquals(2, numbers.size()),
                () -> assertTrue(numbers.contains("drone1")),
                () -> assertTrue(numbers.contains("drone2")));
    }

}