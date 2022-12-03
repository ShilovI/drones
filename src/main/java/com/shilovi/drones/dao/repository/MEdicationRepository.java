package com.shilovi.drones.dao.repository;

import com.shilovi.drones.dao.entity.MedicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MEdicationRepository extends JpaRepository<MedicationEntity, UUID> {

}