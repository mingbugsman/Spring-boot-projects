package com.TicketSelling.TicketSelling.Entity;

import com.TicketSelling.TicketSelling.Enum.SeatStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


@Entity
@Table(name = "seat")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(name = "Seat_Status", nullable = false)
    SeatStatus seatStatus = SeatStatus.AVAILABLE;


    @Column(name = "no", nullable = false)
    Integer seatNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_category_id", nullable = false)
    SeatCategory seatCategory;


    // date create new seat
    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    // date update seat
    @UpdateTimestamp
    @Column(name = "updated_At")
    LocalDateTime updatedAt;

    @Column(name = "deleted_at", nullable = true)
    LocalDateTime deletedAt;
}
