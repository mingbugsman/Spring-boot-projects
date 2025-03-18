package com.ZZZZ.UserService.DTO.Request;


import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    @NotNull(message = "Email is required")
    String email;


    @NotNull(message = "Password is required")
    String password;
}
