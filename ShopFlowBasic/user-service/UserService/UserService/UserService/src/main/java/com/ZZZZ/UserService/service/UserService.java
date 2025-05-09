package com.ZZZZ.UserService.service;


import com.ZZZZ.UserService.DTO.Request.UserCreationRequest;
import com.ZZZZ.UserService.DTO.Request.UserUpdateInformationRequest;
import com.ZZZZ.UserService.DTO.Response.UserResponse;
import com.ZZZZ.commonDTO.EmailRequest;
import org.springframework.data.domain.Page;

public interface UserService {
    UserResponse updateUser(String id, UserUpdateInformationRequest request);
    void softDeleteUser(String id);
    void absoluteDeleteUser(String id);
    UserResponse getUser(String id);
    Page<UserResponse> getAll(int page, int size, String sortBy);
    boolean verifyEmail(String email, String otp);
    String sendOTP(EmailRequest request);
}