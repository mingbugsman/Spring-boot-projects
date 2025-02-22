package com.TicketSelling.TicketSelling.DTO.Request.Payment;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ShowInfoPaymentRequest {
    @NotNull(message = "customer id is required")
    String customerId;

    @NotNull(message = "seatIds is required")
    List<String> seatIds;

}
