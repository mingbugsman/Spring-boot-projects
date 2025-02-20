package com.TicketSelling.TicketSelling.Mapper;


import com.TicketSelling.TicketSelling.DTO.Request.SeatCategory.SeatCategoryCreationRequest;
import com.TicketSelling.TicketSelling.DTO.Request.SeatCategory.SeatCategoryUpdateRequest;
import com.TicketSelling.TicketSelling.DTO.Response.SeatCategory.SeatCategoryResponse;
import com.TicketSelling.TicketSelling.Entity.SeatCategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SeatCategoryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "hall", ignore = true)
    @Mapping(target = "seats", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    SeatCategory toSeatCategory(SeatCategoryCreationRequest request);



    @Mapping(target = "id", ignore = true)
    @Mapping(target = "hall", ignore = true)
    @Mapping(target = "seats", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateSeatCategory(@MappingTarget SeatCategory seatCategory, SeatCategoryUpdateRequest request);



    @Mapping(target = "hallName", source = "hall.hallName")
    SeatCategoryResponse toSeatCategoryResponse(SeatCategory seatCategory);
}
