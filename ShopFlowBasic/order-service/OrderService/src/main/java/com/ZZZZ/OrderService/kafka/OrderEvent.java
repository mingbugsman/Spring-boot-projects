package com.ZZZZ.OrderService.kafka;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderEvent {
    String productId;
    String userId;
    int quantity;
    String locationShipping;
    String message;
}
