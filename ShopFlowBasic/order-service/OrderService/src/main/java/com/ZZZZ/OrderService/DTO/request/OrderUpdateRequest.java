package com.ZZZZ.OrderService.DTO.request;


import com.ZZZZ.OrderService.Enum.OrderStatus;
import com.ZZZZ.OrderService.Enum.PaymentMethod;
import jakarta.validation.constraints.NotBlank;
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
public class OrderUpdateRequest {

    @Positive(message = "quantity is required")
    int quantity;
    @NotBlank(message = "location is not blank")
    String locationShipping;
    PaymentMethod paymentMethod;
}
