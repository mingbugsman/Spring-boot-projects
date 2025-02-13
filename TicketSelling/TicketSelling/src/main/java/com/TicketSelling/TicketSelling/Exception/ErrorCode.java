package com.TicketSelling.TicketSelling.Exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1001, "Uncategorized", HttpStatus.BAD_REQUEST ),
    EMAIL_EXISTED(1002, "Email is existed", HttpStatus.BAD_REQUEST),
    NOT_FOUND_ID(1003,"Id is not existed",HttpStatus.NOT_FOUND);


    int code;
    String message;
    HttpStatus statusCode;



}
