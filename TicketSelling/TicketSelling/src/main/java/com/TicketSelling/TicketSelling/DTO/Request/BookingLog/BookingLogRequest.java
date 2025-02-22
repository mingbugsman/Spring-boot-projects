package com.TicketSelling.TicketSelling.DTO.Request.BookingLog;

import com.TicketSelling.TicketSelling.Enum.BookingLogAction;
import com.TicketSelling.TicketSelling.Enum.BookingStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;


@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookingLogRequest {
    BookingLogAction action;
    BookingStatus bookingStatus;
    String customerId;
    String bookingId;
    LocalDateTime ExpiredTimePayment;
    String detail;
}
