package com.ZZZZ.commonDTO.Order;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderCanceledEvent {
    String orderId;
    String userId;
    String reason;
    LocalDateTime createdAt;
}
