package com.PhoneInventoryManager.PhoneInventory.Entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "Specifications")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Specification {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @OneToOne
    @JoinColumn(name = "phone_id", nullable = false)
    private Phone phone;

    private String storage;
    private String ram;

    @Column(name="screen_size")
    private Double screenSize;

    private String camera;
    private String battery;
    private String os;
}
