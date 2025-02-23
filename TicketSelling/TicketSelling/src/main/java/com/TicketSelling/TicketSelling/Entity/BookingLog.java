package com.TicketSelling.TicketSelling.Entity;

import com.TicketSelling.TicketSelling.Enum.BookingLogAction;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Table(name = "booking_log", indexes = {
        @Index(name = "idx_created_at", columnList = "created_at")
})
public class BookingLog {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Enumerated(EnumType.STRING)
    @JoinColumn(name = "log_action", nullable = false)
    BookingLogAction action;

    @Column(columnDefinition = "TEXT", name = "detail")
    String details;
    @Column(name = "booking_id", nullable = false)
    String bookingId;
    @Column(name = "customer_id", nullable = false)
    String customerId;


    // date add log
    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    // date update log
    @UpdateTimestamp
    @Column(name = "updated_At")
    LocalDateTime updatedAt;


}
