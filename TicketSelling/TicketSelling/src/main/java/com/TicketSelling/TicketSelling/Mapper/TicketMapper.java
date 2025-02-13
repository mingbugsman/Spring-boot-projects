package com.TicketSelling.TicketSelling.Mapper;

import com.TicketSelling.TicketSelling.DTO.Request.Ticket.TicketCreationRequest;
import com.TicketSelling.TicketSelling.DTO.Request.Ticket.TicketUpdateRequest;
import com.TicketSelling.TicketSelling.DTO.Response.Booking.BookingResponse;
import com.TicketSelling.TicketSelling.DTO.Response.Concert.ConcertDetailResponse;
import com.TicketSelling.TicketSelling.DTO.Response.Seat.SeatResponse;
import com.TicketSelling.TicketSelling.DTO.Response.Ticket.TicketDetailResponse;
import com.TicketSelling.TicketSelling.DTO.Response.Ticket.TicketResponse;
import com.TicketSelling.TicketSelling.Entity.Ticket;
import com.TicketSelling.TicketSelling.Enum.TicketStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Mapper(componentModel = "spring", uses = {BookingMapper.class, SeatMapper.class, ConcertMapper.class})
public interface TicketMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "booking", ignore = true)
    @Mapping(target = "concert", ignore = true)
    @Mapping(target = "seat", ignore = true)
    @Mapping(target = "ticketStatus", ignore = true)
    Ticket toTicket(TicketCreationRequest request);

    @Mapping(target = "concertId", source = "id.concertId")
    @Mapping(target = "seatId", source = "id.seatId")
    @Mapping(target = "bookingId",source = "booking.id")
    @Mapping(target = "lastUpdated", source = "updatedAt")
    TicketResponse toTicketResponse(Ticket ticket);

    @Mapping(target = "booking", ignore = true)
    @Mapping(target = "concert", ignore = true)
    @Mapping(target = "seat", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateTicket(@MappingTarget Ticket ticket, TicketUpdateRequest request);


}
