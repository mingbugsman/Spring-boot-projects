package com.TicketSelling.TicketSelling.Entity;

import com.TicketSelling.TicketSelling.Enum.SeatStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name = "seat")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Enumerated(EnumType.STRING)
    @Column(name = "Seat_Status", nullable = false)
    SeatStatus seatStatus = SeatStatus.AVAILABLE;

    @Column(nullable = false, columnDefinition = "DECIMAL")
    private BigDecimal price; // Giá vé theo ghế

    @Column(name = "row_no", nullable = false)
    Integer rowNumber;

    @Column(name = "no", nullable = false)
    Integer seatNumber;

    @ManyToOne
    @JoinColumn(name = "hall_id", nullable = false)
    Hall hall;

    // date create new seat
    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    // date update seat
    @UpdateTimestamp
    @Column(name = "updated_At")
    LocalDateTime updatedAt;
}
