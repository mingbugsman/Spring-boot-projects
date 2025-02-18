package com.TicketSelling.TicketSelling.DTO.Request.Hall;

import com.TicketSelling.TicketSelling.Enum.HallStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HallUpdateRequest {

    String hallName;
    String address;
    String hallInformation;
    HallStatus hallStatus;
    List<String> seatCategoryIds;
    List<String> concertIds;

}
