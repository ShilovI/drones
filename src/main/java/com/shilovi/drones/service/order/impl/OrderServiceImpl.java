package com.shilovi.drones.service.order.impl;

import com.shilovi.drones.dao.access.OrderAccessService;
import com.shilovi.drones.model.OrderDto;
import com.shilovi.drones.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderAccessService service;

    @Autowired
    public OrderServiceImpl(OrderAccessService service) {
        this.service = service;
    }

    @Override
    public OrderDto place(OrderDto order) {
        return service.place(order);
    }

}
