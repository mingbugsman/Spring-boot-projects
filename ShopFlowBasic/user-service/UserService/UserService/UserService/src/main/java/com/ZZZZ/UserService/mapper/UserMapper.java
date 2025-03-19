package com.ZZZZ.UserService.mapper;


import com.ZZZZ.UserService.DTO.Request.UserCreationRequest;
import com.ZZZZ.UserService.DTO.Request.UserUpdateInformationRequest;
import com.ZZZZ.UserService.DTO.Response.UserResponse;
import com.ZZZZ.UserService.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toUser(UserCreationRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        return user;
    }

    public void updateUser(User user, UserUpdateInformationRequest request) {
        user.setUsername(request.getUsername() != null  ? request.getUsername() : user.getEmail());
        user.setEmail(request.getEmail() != null  ? request.getEmail() : user.getEmail());
    }

    public UserResponse toUserResponse(User user) {
        return new UserResponse(user.getId(), user.getUsername(), user.getEmail());
    }
}