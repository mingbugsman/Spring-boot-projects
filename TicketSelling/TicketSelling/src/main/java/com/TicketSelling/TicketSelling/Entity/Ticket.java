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
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "ticket")
public class Ticket {

    @EmbeddedId
    TicketPK id;

    @Enumerated(EnumType.STRING)
    @Column(name = "Ticket_Status", nullable = false)
    TicketStatus status = TicketStatus.ACTIVE;

    // belongs to seat
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "belongs_to", nullable = false, insertable = false, updatable = false)
    Seat seat;

    // ticket for concert
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "for_concert", nullable = false, insertable = false,updatable = false)
    Concert concert;

    // book_id for ticket
    @ManyToOne
    @JoinColumn(name = "booking_id", nullable = false)
    Booking booking;

    @ManyToOne
    @JoinColumn(name = "ticket_price_id", nullable = false)
    TicketPrice ticketPrice;

    // date adđ new ticket
    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    // date update ticket
    @UpdateTimestamp
    @Column(name = "updated_At")
    LocalDateTime updatedAt;
}
