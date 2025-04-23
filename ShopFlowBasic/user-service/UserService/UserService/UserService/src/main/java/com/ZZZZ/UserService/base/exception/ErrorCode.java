package com.ZZZZ.UserService.base.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    UNAUTHORIZED(444, "Unauthorized", HttpStatus.FORBIDDEN),
    UNAUTHENTICATED(555, "Unauthenticated", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(667,"Invalid password", HttpStatus.BAD_REQUEST),
    INVALID_AUTHORIZATION_HEADER(777,"Invalid authorization header", HttpStatus.BAD_REQUEST),
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),

    INVALID_JSON_FORMAT(1000,"Invalid request format : ", HttpStatus.BAD_REQUEST),
    INVALID_KEY(1001, "Uncategorized", HttpStatus.BAD_REQUEST),

    /**
     * HANDLING ERROR EXISTED
     */
    USER_EXISTED(1002, "Already existed user", HttpStatus.BAD_REQUEST),
    USERNAME_EXISTED(1003, "Already existed username", HttpStatus.BAD_REQUEST),
    EMAIL_EXISTED(1004, "Already existed email", HttpStatus.BAD_REQUEST),
    EMAIL_IS_VERIFIED(1005, "Email has been verified", HttpStatus.BAD_REQUEST),

    /**
     * HANDLING ERROR : NOT FOUND
     */
    USER_NOT_EXISTED(1010, "User is not existed", HttpStatus.NOT_FOUND),
    USERNAME_NOT_EXISTED(1011, "Username is not existed", HttpStatus.NOT_FOUND),
    EMAIL_NOT_EXISTED(1012, "Email is not existed", HttpStatus.NOT_FOUND),
    /**
     * HANDLING ERROR : DELETED
     */
    USER_IS_ALREADY_DELETED(1020, "product is already deleted", HttpStatus.NOT_FOUND),

    // INVALID OTP
    OTP_IS_INVALID(1111, "your otp is expired or invalid, pls verify again", HttpStatus.BAD_REQUEST);

    private final int code;
    private final String message;
    private final HttpStatus statusCode;

    ErrorCode(int code, String message, HttpStatus statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }
}