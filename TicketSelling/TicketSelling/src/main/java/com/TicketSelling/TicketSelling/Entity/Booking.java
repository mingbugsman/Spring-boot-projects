package com.TicketSelling.TicketSelling.Entity;

import com.TicketSelling.TicketSelling.Enum.BookingStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "booking", indexes = {
        @Index(name = "idx_deleted_at", columnList = "deleted_at"),
        @Index(name = "idx_created_at", columnList = "created_at")
})
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Enumerated(EnumType.STRING)
    @Column(name = "booking_status", nullable = false)
    BookingStatus bookingStatus = BookingStatus.PENDING;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    Customer customer;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Ticket> tickets;


    @Column(name = "expired_payment_time", updatable = false, nullable = false)
    private LocalDateTime expiredPaymentTime = LocalDateTime.now().plusMinutes(10);

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;


    @UpdateTimestamp
    @Column(name = "updated_At")
    LocalDateTime updatedAt;

    @Column(name = "deleted_at", nullable = true)
    LocalDateTime deletedAt;



}
