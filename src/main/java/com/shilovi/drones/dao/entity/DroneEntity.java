package com.shilovi.drones.dao.entity;

import com.shilovi.drones.model.base.ActiveType;
import com.shilovi.drones.model.base.DroneModel;
import com.shilovi.drones.model.base.DroneState;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(
        name = "drones",
        indexes = {
                @Index(name = "drone_id_uindex", columnList = "id", unique = true),
                @Index(name = "drone_state_index", columnList = "state")
        }
)
public class DroneEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dronesSeqGenerator")
    @SequenceGenerator(name = "dronesSeqGenerator", sequenceName = "drones_seq", allocationSize = 1)
    @ToString.Include
    private Long id;

    @Column(name = "serial_number")
    @EqualsAndHashCode.Include
    @ToString.Include
    private String serialNumber;

    @Column(name = "weight_limit")
    private Long weightLimit;

    @Column(name = "model")
    @ToString.Include
    private DroneModel model;

    @Column(name = "state")
    @ToString.Include
    private DroneState state;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "drone",
            orphanRemoval = true
    )
    private List<OrderEntity> orders;

    @Column(name = "created", updatable = false)
    private LocalDateTime created;

    @Column(name = "updated")
    private LocalDateTime updated;

    @Builder.Default
    @Column(name = "active", nullable = false)
    private ActiveType active = ActiveType.ENABLED;

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

    @PreRemove
    private void onDelete() {
        updated = LocalDateTime.now();
        active = ActiveType.DISABLED;
    }

}
