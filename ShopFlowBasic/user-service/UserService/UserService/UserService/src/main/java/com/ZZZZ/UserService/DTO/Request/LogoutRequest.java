package com.ZZZZ.UserService.DTO.Request;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogoutRequest {
    @NotNull(message = "refresh token is required")
    private String refreshToken;
}
