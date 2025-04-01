package com.ZZZZ.ProductService.base.exception;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    UNAUTHORIZED(444, "Unauthorized", HttpStatus.FORBIDDEN),
    INVALID_AUTHORIZATION_HEADER(777,"Invalid authorization header", HttpStatus.BAD_REQUEST),
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),

    INVALID_JSON_FORMAT(1000,"Invalid request format : ", HttpStatus.BAD_REQUEST),
    INVALID_KEY(1001, "Uncategorized", HttpStatus.BAD_REQUEST),
    /**
     * HANDLING ERROR EXISTED
     */
    PRODUCT_EXISTED(1002, "Already existed product", HttpStatus.BAD_REQUEST),
    /**
     * HANDLING ERROR : NOT FOUND
     */
    PRODUCT_NOT_EXISTED(1010, "product is not existed", HttpStatus.NOT_FOUND),

    /**
     * HANDLING ERROR : DELETED
     */
    PRODUCT_IS_ALREADY_DELETED(1020, "product is already deleted", HttpStatus.NOT_FOUND);


    private final int code;
    private final String message;
    private final HttpStatus statusCode;

    ErrorCode(int code, String message, HttpStatus statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }
}