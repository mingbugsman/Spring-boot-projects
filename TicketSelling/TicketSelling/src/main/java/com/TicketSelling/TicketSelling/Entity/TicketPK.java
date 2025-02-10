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

    @Column(name = "belongs_to")
    String seatId;

    @Column(name = "for_concert")
    String concertId;

}
