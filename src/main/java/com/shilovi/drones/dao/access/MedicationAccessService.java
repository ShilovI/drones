package com.shilovi.drones.dao.access;

import com.shilovi.drones.model.MedicationDto;
import org.springframework.web.multipart.MultipartFile;

public interface MedicationAccessService {

    MedicationDto save(MedicationDto dto);

    void uploadFile(String code, MultipartFile file);

}
