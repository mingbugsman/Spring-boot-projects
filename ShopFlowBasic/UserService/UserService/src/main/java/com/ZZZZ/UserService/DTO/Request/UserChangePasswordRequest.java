package com.ZZZZ.UserService.DTO.Request;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserChangePasswordRequest {
    @NotNull(message = "email is required")
    private String email;
    @NotNull(message = "password is required")
    private String password;
}