package com.TicketSelling.TicketSelling.Entity;

import com.TicketSelling.TicketSelling.Enum.HallStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "hall")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Hall {

    @Id
    @GeneratedValue(strategy =  GenerationType.UUID)
    String id;

    @Enumerated(EnumType.STRING)
    @JoinColumn(name = "Hall_Status", nullable = false)
    HallStatus hallStatus = HallStatus.AVAILABLE;

    @Column(name = "name", columnDefinition = "TEXT", nullable = false)
    String name;

    @Column(name = "address", columnDefinition = "TEXT", nullable = false)
    String address;

    @Column(name = "information_hall", columnDefinition = "TEXT")
    String informationHall;

    @OneToMany(mappedBy = "hall", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Seat> seats;

    @OneToMany(mappedBy = "hall", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Concert> concerts;

    // date add concert
    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    // date update concert
    @UpdateTimestamp
    @Column(name = "updated_At")
    LocalDateTime updatedAt;
}
