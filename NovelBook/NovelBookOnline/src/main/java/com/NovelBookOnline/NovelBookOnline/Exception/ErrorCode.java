package com.NovelBookOnline.NovelBookOnline.Exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    UNAUTHORIZED(444, "Unauthorized", HttpStatus.FORBIDDEN),
    UNAUTHENTICATED(555, "Unauthenticated", HttpStatus.BAD_REQUEST),
    INVALID_KEY(1001, "Uncategorized", HttpStatus.BAD_REQUEST),
    INVALID_JSON_FORMAT(1000,"Invalid request format : ", HttpStatus.BAD_REQUEST),
    /**
     * HANDLING ERROR EXISTED
     */
    USER_EXISTED(1002, "Already existed user", HttpStatus.BAD_REQUEST),
    USERNAME_EXISTED(1003, "Already existed username", HttpStatus.BAD_REQUEST),
    EMAIL_EXISTED(1004, "Already existed email", HttpStatus.BAD_REQUEST),
    NOVEL_EXISTED(1005, "Already existed novel", HttpStatus.BAD_REQUEST),
    CHAPTER_EXISTED(1006, "Already existed chapter", HttpStatus.BAD_REQUEST),
    CATEGORY_EXISTED(1007, "Already existed category", HttpStatus.BAD_REQUEST),
    AUTHOR_EXISTED(1008, "Already existed author",HttpStatus.BAD_REQUEST),
    /**
     * HANDLING ERROR : NOT FOUND
     */
    USER_NOT_EXISTED(1010, "User is not existed", HttpStatus.NOT_FOUND),
    NOVEL_NOT_EXISTED(1011, "Novel is not existed", HttpStatus.NOT_FOUND),
    CHAPTER_NOT_EXISTED(1012, "Chapter is not existed", HttpStatus.NOT_FOUND),
    CATEGORY_NOT_EXISTED(1013, "Category is not existed", HttpStatus.NOT_FOUND),
    AUTHOR_NOT_EXISTED(1014, "Author is not existed", HttpStatus.NOT_FOUND),
    COMMENT_NOT_EXISTED(1015, "Comment is not existed", HttpStatus.NOT_FOUND),

    /**
     * HANDLING ERROR : DELETED
     */
    ENTITY_IS_ALREADY_DELETED(1020, "entity is already deleted", HttpStatus.NOT_FOUND);


    private final int code;
    private final String message;
    private final HttpStatus statusCode;

    ErrorCode(int code, String message, HttpStatus statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }
}
