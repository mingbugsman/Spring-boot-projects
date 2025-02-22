package com.TicketSelling.TicketSelling.DTO.Request.Payment;

import com.TicketSelling.TicketSelling.DTO.Request.Booking.BookingCreationRequest;
import com.TicketSelling.TicketSelling.Enum.PaymentMethod;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentRequest {
    @NotNull(message = "customer id is required")
    String customerId;

    @NotNull(message = "payment method is required")
    PaymentMethod paymentMethod;

    @NotNull
    @DecimalMin(value = "0.0",inclusive = false, message = "Amount must be greater than 0")
    BigDecimal amount;

    @NotNull(message = "booking request is required")
    BookingCreationRequest bookingCreationRequest;
}
