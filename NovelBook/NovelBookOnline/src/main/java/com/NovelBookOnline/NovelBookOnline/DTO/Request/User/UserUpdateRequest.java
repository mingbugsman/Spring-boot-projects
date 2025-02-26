package com.NovelBookOnline.NovelBookOnline.DTO.Request.User;


import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {
    @NotNull(message = "Username is required")
    String username;

    @NotNull(message = "email is required")
    String email;

    @NotNull(message = "gender is required")
    boolean gender;


    MultipartFile userImageDate;
}
