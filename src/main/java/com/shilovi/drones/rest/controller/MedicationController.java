package com.shilovi.drones.rest.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/content")
@ConditionalOnProperty(prefix = "application.rest.controller", name = "medication", havingValue = "enabled", matchIfMissing = true)
public class MedicationController {
}
