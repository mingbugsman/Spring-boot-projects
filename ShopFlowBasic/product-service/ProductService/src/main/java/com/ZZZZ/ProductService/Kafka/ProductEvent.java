package com.ZZZZ.ProductService.Kafka;

import com.ZZZZ.ProductService.Enum.EventType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductEvent {
    String productId;
    EventType eventType; //
    String message;
}
