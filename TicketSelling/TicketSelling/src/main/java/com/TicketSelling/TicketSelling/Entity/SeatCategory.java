package com.TicketSelling.TicketSelling.Entity;

import com.TicketSelling.TicketSelling.Enum.SeatType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "seat_category", indexes = {
        @Index(name = "idx_deleted_at", columnList = "deleted_at"),
        @Index(name = "idx_created_at", columnList = "created_at")
})
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SeatCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(nullable = false, columnDefinition = "DECIMAL")

    private BigDecimal price;

    @Column(name = "total_seats", nullable = false)
    Integer totalSeats;  // total seat in a row

    @Enumerated(EnumType.STRING)
    @Column(name = "Seat_Type", nullable = false)
    SeatType seatType = SeatType.STANDARD;

    @Column(name = "row_label", nullable = false)
    String rowLabel;  // example : "A", "B", v.v.

    @OneToMany(mappedBy = "seatCategory", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Seat> seats;

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


    @Column(name = "deleted_at", nullable = true)
    LocalDateTime deletedAt;
}
