package com.TicketSelling.TicketSelling.Mapper;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class MapperHelper {
    SeatMapper seatMapper;
    TicketMapper ticketMapper;
    HallMapper hallMapper;
    BandMapper bandMapper;
    ConcertMapper concertMapper;
    BookingMapper bookingMapper;
}
