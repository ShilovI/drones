package com.shilovi.drones.dao.repository;

import com.shilovi.drones.dao.entity.MedicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MedicationRepository extends JpaRepository<MedicationEntity, UUID> {

    Optional<MedicationEntity> findByCode(String code);

}