package com.TicketSelling.TicketSelling.Mapper;

import com.TicketSelling.TicketSelling.DTO.Request.Booking.BookingCreationRequest;
import com.TicketSelling.TicketSelling.DTO.Request.Booking.BookingUpdateRequest;
import com.TicketSelling.TicketSelling.DTO.Response.Booking.BookingResponse;
import com.TicketSelling.TicketSelling.DTO.Response.Booking.BookingTicketsResponse;
import com.TicketSelling.TicketSelling.DTO.Response.Ticket.TicketResponse;
import com.TicketSelling.TicketSelling.Entity.Booking;
import com.TicketSelling.TicketSelling.Enum.BookingStatus;
import com.TicketSelling.TicketSelling.Utils.CastingUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
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

    default BookingTicketsResponse toBookingTicketsResponse(Object[] data) {
        if (data == null || data.length < 4) {
            return null;
        }
        return new BookingTicketsResponse(
                (String) data[0],
                (BookingStatus) data[1],
                (LocalDateTime) data[2],
                CastingUtil.safeCastList(data[3], TicketResponse.class)
        );
    }
}
