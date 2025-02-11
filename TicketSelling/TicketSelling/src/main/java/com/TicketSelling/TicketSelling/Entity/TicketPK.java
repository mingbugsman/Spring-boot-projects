package com.TicketSelling.TicketSelling.Entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;


@Embeddable
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TicketPK implements Serializable {

    @Column(name = "seat_id")
    String seatId;

    @Column(name = "concert_id")
    String concertId;

}
