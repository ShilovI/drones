package com.shilovi.drones.dao.entity;

import com.shilovi.drones.model.base.ActiveType;
import com.shilovi.drones.model.base.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(
        name = "orders",
        indexes = {
                @Index(name = "orders_id_uindex", columnList = "id", unique = true),
                @Index(name = "orders_drone_drone_serial_number_index", columnList = "drone_serial_number", unique = true)
        }
)
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ordersSeqGenerator")
    @SequenceGenerator(name = "ordersSeqGenerator", sequenceName = "orders_seq", allocationSize = 1)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "drone_serial_number")
    @ToString.Include
    private DroneEntity drone;

    @Column(name = "status")
    @ToString.Include
    private OrderStatus status;

    @Builder.Default
    @Column(name = "active", nullable = false)
    private ActiveType active = ActiveType.ENABLED;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "order_medication",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "medication_code"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"order_id", "medication_code"}))
    @Builder.Default
    @ToString.Exclude
    private List<MedicationEntity> medicationEntities = new ArrayList<>();

    @Column(name = "created", updatable = false)
    private LocalDateTime created;

    @Column(name = "updated")
    private LocalDateTime updated;

    @PrePersist
    private void onCreate() {
        var current = LocalDateTime.now();
        created = current;
        updated = current;
    }

    @PreUpdate
    private void onUpdate() {
        updated = LocalDateTime.now();
    }

}
