package com.NovelBookOnline.NovelBookOnline.DTO.Request.Auth;


import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LogoutRequest {

    @NotNull(message = "token is required")
    private String token;
}
