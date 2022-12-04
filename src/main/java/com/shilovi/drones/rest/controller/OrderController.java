package com.shilovi.drones.rest.controller;

import com.shilovi.drones.dao.access.OrderAccessService;
import com.shilovi.drones.model.OrderDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
@RequestMapping("/order")
@ConditionalOnProperty(prefix = "application.rest.controller", name = "order", havingValue = "enabled", matchIfMissing = true)
public class OrderController {

    private final OrderAccessService service;

    @Autowired
    public OrderController(OrderAccessService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(OK)
    public OrderDto place(@RequestBody @Validated OrderDto request) {
        logger.info("Medication registration request={}", request);
        return service.place(request);
    }

}
