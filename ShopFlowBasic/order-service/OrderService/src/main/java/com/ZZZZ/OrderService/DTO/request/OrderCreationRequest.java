package com.ZZZZ.OrderService.DTO.request;


import com.ZZZZ.OrderService.Enum.PaymentMethod;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderCreationRequest {

    @NotNull(message = "product id is required")
    String productId;

    @NotNull(message = "user id is required")
    String userId;

    @NotNull(message = "quantity is required")
    @Positive(message = "quantity is required")
    int quantity;
    @NotNull(message = "location shipping is required")
    String locationShipping;

    @NotNull(message = "Payment method is required")
    PaymentMethod paymentMethod;
}
