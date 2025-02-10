package com.TicketSelling.TicketSelling.Entity;

import com.TicketSelling.TicketSelling.Enum.SeatStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;


@Entity
@Table(name = "seat")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Enumerated(EnumType.STRING)
    @Column(name = "Seat_Status", nullable = false)
    SeatStatus seatStatus = SeatStatus.AVAILABLE;

    @Column(name = "row_no", nullable = false)
    Integer rowNumber;

    @Column(name = "no", nullable = false)
    Integer seatNumber;

    @ManyToOne
    @JoinColumn(name = "hall_id", nullable = false)
    Hall hall;

}
