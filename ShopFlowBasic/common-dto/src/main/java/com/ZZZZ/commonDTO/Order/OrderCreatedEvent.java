package com.ZZZZ.commonDTO.Order;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderCreatedEvent {
    String orderId;
    String productId;
    String userId;
    int quantity;
    String locationShipping;
    String message;
}
