package com.ZZZZ.UserService.service;

import com.ZZZZ.UserService.DTO.Request.UserCreationRequest;
import com.ZZZZ.UserService.DTO.Request.UserUpdateInformationRequest;
import com.ZZZZ.UserService.DTO.Response.UserResponse;
import org.springframework.data.domain.Page;

public interface UserService {
    UserResponse createUser(UserCreationRequest request);
    UserResponse updateUser(UserUpdateInformationRequest request);
    void softDeleteUser(String id);
    void absoluteDeleteUser(String id);
    UserResponse getUser(String id);
    Page<UserResponse> getAll();
}
