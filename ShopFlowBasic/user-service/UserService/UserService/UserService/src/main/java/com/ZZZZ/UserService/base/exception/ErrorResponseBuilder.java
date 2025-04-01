package com.ZZZZ.UserService.base.exception;

import com.ZZZZ.UserService.DTO.Response.ApiResponse;

import java.util.List;

public class ErrorResponseBuilder {
    public static <T> ApiResponse<T> build(ErrorCode errorCode, String customMessage) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setCode(errorCode.getCode());
        response.setMessage(customMessage != null ? customMessage : errorCode.getMessage());
        return response;
    }

    public static <T> ApiResponse<T> buildValidationError(List<String> messages, ErrorCode errorCode) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setCode(errorCode.getCode());
        response.setMessage(String.join("; \n" + messages));
        return response;
    }
}