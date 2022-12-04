package com.shilovi.drones.dao.access;

import com.shilovi.drones.model.OrderDto;

public interface OrderAccessService {

    OrderDto place(OrderDto dto);

}
