package com.NovelBookOnline.NovelBookOnline.DTO.Request.Auth;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RefreshTokenRequest {
    @NotNull(message = "Token is required")
    private String refreshToken;
}
