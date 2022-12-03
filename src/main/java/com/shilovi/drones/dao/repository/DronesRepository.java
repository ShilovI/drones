package com.shilovi.drones.dao.repository;

import com.shilovi.drones.dao.entity.DroneEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DronesRepository extends JpaRepository<DroneEntity, UUID> {

    Optional<DroneEntity> findBySerialNumber(String serialNumber);

}