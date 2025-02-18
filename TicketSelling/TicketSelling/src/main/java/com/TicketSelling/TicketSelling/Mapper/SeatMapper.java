package com.TicketSelling.TicketSelling.Mapper;

import com.TicketSelling.TicketSelling.DTO.Request.Seat.SeatCreationRequest;
import com.TicketSelling.TicketSelling.DTO.Request.Seat.SeatUpdateRequest;
import com.TicketSelling.TicketSelling.DTO.Response.Seat.SeatResponse;
import com.TicketSelling.TicketSelling.Entity.Seat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SeatMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "seatStatus", ignore = true)
    @Mapping(target = "seatCategory", ignore = true)
    @Mapping(target ="deletedAt", ignore = true)
    Seat toSeat(SeatCreationRequest request);

    @Mapping(target = "hallName", source = "seatCategory.hall.hallName")
    SeatResponse toSeatResponse(Seat seat);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "seatCategory", ignore = true)
    @Mapping(target ="deletedAt", ignore = true)
    void updateSeat(@MappingTarget Seat seat, SeatUpdateRequest request);
}
