package com.TicketSelling.TicketSelling.Mapper.CustomMapper;

import com.TicketSelling.TicketSelling.DTO.Response.Concert.ConcertDetailResponse;
import com.TicketSelling.TicketSelling.DTO.Response.Ticket.TicketDetailResponse;
import com.TicketSelling.TicketSelling.Entity.Band;
import com.TicketSelling.TicketSelling.Entity.Concert;
import com.TicketSelling.TicketSelling.Entity.Ticket;
import com.TicketSelling.TicketSelling.Mapper.BandMapper;
import com.TicketSelling.TicketSelling.Mapper.BookingMapper;
import com.TicketSelling.TicketSelling.Mapper.ConcertMapper;
import com.TicketSelling.TicketSelling.Mapper.SeatMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

public class CustomTicketMapper {
    SeatMapper seatMapper;
    ConcertMapper concertMapper;
    BookingMapper bookingMapper;
    BandMapper bandMapper;

    public TicketDetailResponse toTicketDetailResponse(Ticket ticket) {
        Concert concert = ticket.getConcert();
        List<Band> bands = new ArrayList<>(concert.getBands());

        ConcertDetailResponse concertDetailResponse = new ConcertDetailResponse(
              concert.getId(),
              concert.getDate(),
              concert.getStartDate(),
              concert.getConcertName(),
              concert.getConcertStatus(),
              concert.getHall().getHallName(),
                concert.getConcertInformation(),
                concert.getUpdatedAt(),
                bands.stream().map(bandMapper::toBandResponse).toList()
        );

        return new TicketDetailResponse(
                ticket.getTicketStatus(),
                seatMapper.toSeatResponse(ticket.getSeat()),
                concertDetailResponse,
                bookingMapper.toBookingResponse(ticket.getBooking()),
                ticket.getPrice(),
                ticket.getCreatedAt(),
                ticket.getUpdatedAt()
        );
    }
}
