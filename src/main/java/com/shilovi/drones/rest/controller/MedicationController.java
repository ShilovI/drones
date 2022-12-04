package com.shilovi.drones.rest.controller;

import com.shilovi.drones.dao.access.MedicationAccessService;
import com.shilovi.drones.model.MedicationDto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
@RequestMapping("/medication")
@ConditionalOnProperty(prefix = "application.rest.controller", name = "medication", havingValue = "enabled", matchIfMissing = true)
public class MedicationController {

    private final MedicationAccessService service;

    @Autowired
    public MedicationController(MedicationAccessService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(OK)
    public MedicationDto register(@RequestBody @Validated MedicationDto request) {
        logger.info("Medication registration request={}", request);
        return service.save(request);
    }

    @PostMapping(value = "/image/{code}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void uploadFile(@PathVariable("code") @NotNull String code,
                           @NotEmpty @RequestParam("files") MultipartFile file) {
        logger.info("Attach image to medication {} name {}", code, file.getName());
        service.uploadFile(code, file);
    }

}
