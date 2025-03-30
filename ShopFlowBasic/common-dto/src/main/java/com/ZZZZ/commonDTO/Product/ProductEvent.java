package com.ZZZZ.commonDTO.Product;


import com.ZZZZ.commonDTO.Enum.EventType;
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
