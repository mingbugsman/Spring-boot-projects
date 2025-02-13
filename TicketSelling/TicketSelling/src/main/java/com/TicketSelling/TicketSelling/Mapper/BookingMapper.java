package com.TicketSelling.TicketSelling.Mapper;

import com.TicketSelling.TicketSelling.DTO.Request.Booking.BookingCreationRequest;
import com.TicketSelling.TicketSelling.DTO.Request.Booking.BookingUpdateRequest;
import com.TicketSelling.TicketSelling.DTO.Response.Booking.BookingResponse;
import com.TicketSelling.TicketSelling.DTO.Response.Booking.BookingTicketsResponse;
import com.TicketSelling.TicketSelling.Entity.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, uses = {TicketMapper.class})
public interface BookingMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "tickets", ignore = true)
    @Mapping(target = "bookingStatus", ignore = true)
    Booking toBooking(BookingCreationRequest request);

    @Mapping(target = "customerId", source = "customer.id")
    BookingResponse toBookingResponse(Booking booking);

    @Mapping(target = "tickets", ignore = true)
    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    void updateBooking(@MappingTarget Booking booking, BookingUpdateRequest request);

/*
    @Mapping(target = "lastUpdate", source = "updatedAt")
    @Mapping(target = "tickets", source = "tickets")
    BookingTicketsResponse toBookingTicketsResponse(Booking booking);
*/

}
