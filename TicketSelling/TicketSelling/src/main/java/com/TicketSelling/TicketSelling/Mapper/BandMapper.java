package com.TicketSelling.TicketSelling.Mapper;


import com.TicketSelling.TicketSelling.DTO.Request.Band.BandCreationRequest;
import com.TicketSelling.TicketSelling.DTO.Request.Band.BandUpdateRequest;
import com.TicketSelling.TicketSelling.DTO.Response.Band.BandDetailResponse;
import com.TicketSelling.TicketSelling.DTO.Response.Band.BandResponse;
import com.TicketSelling.TicketSelling.DTO.Response.Concert.ConcertResponse;
import com.TicketSelling.TicketSelling.Entity.Band;
import com.TicketSelling.TicketSelling.Enum.BandStatus;
import com.TicketSelling.TicketSelling.Utils.CastingUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BandMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "bandStatus",ignore = true)
    @Mapping(target = "concerts", ignore = true)
    Band toBand(BandCreationRequest request);


    BandResponse toBandResponse(Band band);

    @Mapping(target = "concerts", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateBand(@MappingTarget Band band, BandUpdateRequest request);


    default BandDetailResponse toBandDetailResponse(Object[] data) {
        if (data == null || data.length < 6) {
            return null;
        }
        return new BandDetailResponse(
                (String) data[0],
                (String) data[1],
                (BandStatus) data[2],
                (String) data[3],
                (LocalDateTime) data[4],
                CastingUtil.safeCastList(data[5], ConcertResponse.class)
        );
    }
}
