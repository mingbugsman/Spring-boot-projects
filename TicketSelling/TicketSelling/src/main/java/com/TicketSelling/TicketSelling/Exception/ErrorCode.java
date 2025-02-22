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
    ADDRESS_EXISTED(1002, "ADDRESS IS EXISTED", HttpStatus.BAD_REQUEST),
    EMAIL_EXISTED(1003, "Email is existed", HttpStatus.BAD_REQUEST),
    NOT_FOUND_ID(1004,"Id is not existed",HttpStatus.NOT_FOUND),
    BAND_EXISTED(1005, "Band is existed",HttpStatus.BAD_REQUEST ),
    CONCERT_EXISTED(1006, "Concert is existed", HttpStatus.BAD_REQUEST),
    SEAT_EXISTED(1007, "Seat is existed", HttpStatus.BAD_REQUEST),
    TICKET_EXISTED(1008, "Ticket is existed", HttpStatus.BAD_REQUEST),



    ENTITY_IS_ALREADY_DELETED(1020,"ENTITY HAS BEEN DELETED", HttpStatus.BAD_REQUEST),
    TICKET_ALREADY_BOUGHT(1050, "Ticket is already bought", HttpStatus.BAD_REQUEST),
    SEAT_NOT_AVAILABLE(1070, "Seat is occupied", HttpStatus.BAD_REQUEST),
    PAYMENT_TIMEOUT_ERROR(1111, "The user has missed the payment period", HttpStatus.BAD_REQUEST),
    PAYMENT_REQUIRED_MONEY_ERROR(1112, "The amount is not enough to pay", HttpStatus.BAD_REQUEST);
    int code;
    String message;
    HttpStatus statusCode;



}
