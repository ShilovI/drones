package com.shilovi.drones.dao.access.impl;

import com.shilovi.drones.dao.access.DronesAccessService;
import com.shilovi.drones.dao.entity.DroneEntity;
import com.shilovi.drones.dao.entity.OrderEntity;
import com.shilovi.drones.dao.repository.DronesRepository;
import com.shilovi.drones.dao.repository.OrderRepository;
import com.shilovi.drones.exception.NotFoundException;
import com.shilovi.drones.model.DroneDto;
import com.shilovi.drones.model.OrderDto;
import com.shilovi.drones.model.base.DroneState;
import com.shilovi.drones.model.base.OrderStatus;
import com.shilovi.drones.utilities.mapper.DroneDtoToDroneEntityMapper;
import com.shilovi.drones.utilities.mapper.DroneEntityToDroneDtoMapper;
import com.shilovi.drones.utilities.mapper.OrderEntityToOrderDtoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class DronesAccessServiceImpl implements DronesAccessService {

    private final DronesRepository repository;
    private final OrderRepository orderRepository;

    @Autowired
    DronesAccessServiceImpl(DronesRepository repository,
                            OrderRepository orderRepository) {
        this.repository = repository;
        this.orderRepository = orderRepository;
    }

    @Override
    public DroneDto save(DroneDto dto) {
        return DroneEntityToDroneDtoMapper.map(repository.save(DroneDtoToDroneEntityMapper.map(dto)));
    }

    @Override
    public DroneDto findBySerialNumber(String serialNumber) {
        return repository.findBySerialNumber(serialNumber)
                .map(DroneEntityToDroneDtoMapper::map)
                .orElseThrow(() -> new NotFoundException("Drone wasn't found by serial number : %s"
                        .formatted(serialNumber)));
    }

    @Override
    public boolean doesDroneExists(String serialNumber) {
        return repository.doesExist(serialNumber);
    }

    @Override
    public void assignAnOrder(Long orderId, String serialNumber) {
        OrderEntity order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Order wasn't found by id : %s"
                        .formatted(serialNumber)));
        DroneEntity drone = repository.findBySerialNumber(serialNumber)
                .orElseThrow(() -> new NotFoundException("Drone wasn't found by serial number : %s"
                        .formatted(serialNumber)));
        //TODO fix N+1 problem
        drone.getOrders().stream().filter(o -> !o.getStatus().equals(OrderStatus.DELIVERED)).findFirst().ifPresent(e -> {
            throw new UnsupportedOperationException("Drone %s already handling order number %d"
                    .formatted(serialNumber, e.getId()));
        });
//Worse way in my opinion, but no N+1 problem
//        if(!drone.getState().equals(DroneState.IDLE)) {
//            throw new UnsupportedOperationException("Drone %s busy!"
//                    .formatted(serialNumber));
//        }

        drone.getOrders().add(order);
        repository.save(drone);
    }

    @Override
    public OrderDto getOrderByDrone(String serialNumber) {
        return repository.findBySerialNumber(serialNumber)
                .orElseThrow(() -> new NotFoundException("Drone wasn't found by serial number : %s"
                        .formatted(serialNumber)))
                .getOrders()
                .stream()
                .filter(o -> !o.getStatus().equals(OrderStatus.DELIVERED))
                .findFirst()
                .map(OrderEntityToOrderDtoMapper::map)
                .orElseThrow(() -> new UnsupportedOperationException("Drone %s doesn't handling any order!"
                        .formatted(serialNumber)));
    }

    @Override
    public Collection<DroneDto> getAllFreeDrones() {
        return repository.findAllByState(DroneState.IDLE)
                .stream()
                .map(DroneEntityToDroneDtoMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<String> findAllSerialNumbers() {
        return repository.findAllSerialNumbers();
    }

}
