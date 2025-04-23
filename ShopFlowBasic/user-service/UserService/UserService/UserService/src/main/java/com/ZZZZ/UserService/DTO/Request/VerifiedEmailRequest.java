package com.ZZZZ.UserService.DTO.Request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class VerifiedEmailRequest {
    @NotNull(message = "email is required")
    private String email;

    @NotNull(message = "otp is required")
    @Length(min = 6, max = 6, message = "otp is required 6 digits")
    private String otp;
}
