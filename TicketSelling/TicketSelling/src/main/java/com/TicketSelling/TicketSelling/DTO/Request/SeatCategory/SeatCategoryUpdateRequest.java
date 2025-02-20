package com.TicketSelling.TicketSelling.DTO.Request.SeatCategory;

import com.TicketSelling.TicketSelling.Enum.SeatType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SeatCategoryUpdateRequest {
    BigDecimal price;
    Integer totalSeats;
    SeatType seatType;
    String rowLabel;
    String hallId;
    List<String> seatIds;
}
