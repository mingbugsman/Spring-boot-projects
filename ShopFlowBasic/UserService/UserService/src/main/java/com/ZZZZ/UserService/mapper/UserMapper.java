package com.ZZZZ.UserService.mapper;


import com.ZZZZ.UserService.DTO.Request.UserCreationRequest;
import com.ZZZZ.UserService.DTO.Request.UserUpdateInformationRequest;
import com.ZZZZ.UserService.DTO.Response.UserResponse;
import com.ZZZZ.UserService.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toUser(UserCreationRequest request) {
         return User.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .build();
    }

    public void updateUser(User user, UserUpdateInformationRequest request) {
        user.setUsername(user.getUsername());
        user.setEmail(user.getEmail());
    }

    public UserResponse toUserResponse(User user) {
        return new UserResponse(user.getId(), user.getUsername(), user.getEmail());
    }
}
