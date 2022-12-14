package com.shilovi.drones.dao.repository;

import com.shilovi.drones.dao.entity.DroneEntity;
import com.shilovi.drones.model.base.DroneState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface DronesRepository extends JpaRepository<DroneEntity, UUID> {

    Optional<DroneEntity> findBySerialNumber(String serialNumber);

    @Query(value = """
            SELECT
            EXISTS(SELECT 1 FROM drones WHERE serial_number =:serialNumber)
            """,
            nativeQuery = true)
    boolean doesExist(String serialNumber);

    @Query(value = "SELECT d.serialNumber FROM DroneEntity d")
    Collection<String> findAllSerialNumbers();

    Collection<DroneEntity> findAllByState(DroneState state);

}