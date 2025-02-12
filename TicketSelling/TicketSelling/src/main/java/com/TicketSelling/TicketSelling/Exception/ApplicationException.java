package com.TicketSelling.TicketSelling.Exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ApplicationException extends RuntimeException {
    private ErrorCode errorCode;
    public ApplicationException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
