package com.shilovi.drones.dao.access.impl;

import com.shilovi.drones.config.properties.DroneProperties;
import com.shilovi.drones.dao.access.OrderAccessService;
import com.shilovi.drones.dao.entity.MedicationEntity;
import com.shilovi.drones.dao.entity.OrderEntity;
import com.shilovi.drones.dao.repository.MedicationRepository;
import com.shilovi.drones.dao.repository.OrderRepository;
import com.shilovi.drones.exception.NotFoundException;
import com.shilovi.drones.model.OrderDto;
import com.shilovi.drones.utilities.mapper.OrderEntityToOrderDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@Transactional
public class OrderAccessServiceImpl implements OrderAccessService {

    private final MedicationRepository medicationRepository;
    private final OrderRepository orderRepository;
    private final DroneProperties properties;

    @Autowired
    public OrderAccessServiceImpl(MedicationRepository medicationRepository,
                                  OrderRepository orderRepository,
                                  DroneProperties properties) {
        this.medicationRepository = medicationRepository;
        this.orderRepository = orderRepository;
        this.properties = properties;
    }

    @Override
    public OrderDto place(OrderDto dto) {
        //medication can be also stored in second level cache
        OrderEntity entity = orderRepository.save(OrderEntity.builder()
                .medicationEntities(dto.getMedication().stream()
                        .map(code -> medicationRepository.findByCode(code)
                                .orElseThrow(() -> new NotFoundException("Medication not found by : %s".formatted(code))))
                        .collect(Collectors.toList()))
                .build());
        //max weight for drones can be also founded in database
        Long orderWeight = entity.getMedicationEntities().stream().mapToLong(MedicationEntity::getWeight).sum();
        if (orderWeight > properties.getMaximumCarryWeight()) {
            throw new UnsupportedOperationException("Order id too heavy %d gram for our drones! Reduce weight to %d grams"
                    .formatted(orderWeight, properties.getMaximumCarryWeight()));
        }
        return OrderEntityToOrderDtoMapper.map(orderRepository.save(entity));
    }

}
