package com.shilovi.drones.dao.entity;

import com.shilovi.drones.model.base.ActiveType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(
        name = "medication",
        indexes = {
                @Index(name = "medication_id_uindex", columnList = "id", unique = true),
                @Index(name = "medication_id_uindex", columnList = "code", unique = true)
        }
)
public class MedicationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "medicationSeqGenerator")
    @SequenceGenerator(name = "medicationSeqGenerator", sequenceName = "medication_seq", allocationSize = 1)
    @ToString.Include
    private Long id;

    @Column(name = "name")
    @ToString.Include
    private String name;

    @Column(name = "weight")
    @ToString.Include
    private Long weight;

    @Column(name = "code")
    @ToString.Include
    @EqualsAndHashCode.Include
    private String code;

    @Column(name = "image")
    private byte[] image;

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
