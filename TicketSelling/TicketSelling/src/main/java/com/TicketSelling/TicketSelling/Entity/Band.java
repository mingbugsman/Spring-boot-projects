package com.TicketSelling.TicketSelling.Entity;

import com.TicketSelling.TicketSelling.Enum.BandStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "band")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Band {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Enumerated(EnumType.STRING)
    @JoinColumn(name = "band_status", nullable = false)
    BandStatus bandStatus = BandStatus.ACTIVE;

    @Column(name = "band_name", columnDefinition = "TEXT",nullable = false)
    String bandName;

    @Column(name = "band_information", columnDefinition = "TEXT",nullable = false)
    String bandInformation;

    @ManyToMany
    @JoinTable(name = "band_playing",
    joinColumns = @JoinColumn(name = "band_id"),
    inverseJoinColumns = @JoinColumn(name = "concert_id"))
    Set<Concert> concerts;


    // date adđ band
    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    // date update band
    @UpdateTimestamp
    @Column(name = "updated_At")
    LocalDateTime updatedAt;

}
