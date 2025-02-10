package com.TicketSelling.TicketSelling.Entity;

import com.TicketSelling.TicketSelling.Enum.BandStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@Table(name = "band")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Band {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Enumerated(EnumType.STRING)
    @JoinColumn(name = "band_status", nullable = false)
    BandStatus bandStatus = BandStatus.ACTIVE;

    @Column(name = "name", columnDefinition = "TEXT",nullable = false)
    String name;

    @Column(name = "address", columnDefinition = "TEXT",nullable = false)
    String address;

    @ManyToMany
    @JoinTable(name = "band_playing",
    joinColumns = @JoinColumn(name = "band_id"),
    inverseJoinColumns = @JoinColumn(name = "concert_id"))
    Set<Concert> concerts;

}
