package com.TicketSelling.TicketSelling.Entity;


import com.TicketSelling.TicketSelling.Enum.TicketStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "ticket")
public class Ticket {

    @EmbeddedId
    TicketPK id;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(name = "Ticket_Status", nullable = false)
    TicketStatus ticketStatus = TicketStatus.PENDING;

    // belongs to seat
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("seatId")
    @JoinColumn(name = "seat_id", nullable = false, insertable = false, updatable = false)
    Seat seat;

    // ticket for concert
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("concertId")
    @JoinColumn(name = "concert_id", nullable = false, insertable = false,updatable = false)
    Concert concert;

    // book_id for ticket
    @ManyToOne
    @JoinColumn(name = "booking_id", nullable = false)
    Booking booking;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    // date adđ new ticket
    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    // date update ticket
    @UpdateTimestamp
    @Column(name = "updated_At")
    LocalDateTime updatedAt;

    @Column(name = "deleted_at", nullable = true)
    LocalDateTime deletedAt;
}
