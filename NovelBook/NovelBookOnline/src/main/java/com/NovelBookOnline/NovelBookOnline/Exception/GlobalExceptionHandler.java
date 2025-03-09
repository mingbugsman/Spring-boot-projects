package com.NovelBookOnline.NovelBookOnline.Exception;




import com.NovelBookOnline.NovelBookOnline.DTO.Response.ApiResponse;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.util.List;


@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleRuntimeException(Exception ex) {
        log.error("Uncategorized exception occurred", ex);
        return ResponseEntity.badRequest()
                .body(ErrorResponseBuilder
                        .build(ErrorCode.UNCATEGORIZED_EXCEPTION, ex.getMessage()));
    }

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ApiResponse<?>> handleApplicationException(ApplicationException ex) {
        ErrorCode errorCode = ex.getErrorCode();
        return ResponseEntity.status(errorCode.getStatusCode())
                .body(ErrorResponseBuilder
                        .build(errorCode, null));
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    public ResponseEntity<ApiResponse<?>> handleAccessDeniedException(AccessDeniedException ex) {
        ErrorCode errorCode = ErrorCode.UNAUTHORIZED;
        return ResponseEntity.status(errorCode.getCode())
                .body(ErrorResponseBuilder.build(errorCode, errorCode.getMessage()));
    }

    @ExceptionHandler(value = AuthorizationDeniedException.class)
    public ResponseEntity<ApiResponse<?>> handleAuthorizationDeniedException(AuthorizationDeniedException ex) {
        ErrorCode errorCode = ErrorCode.UNAUTHORIZED;
        return ResponseEntity.status(errorCode.getCode())
                .body(ErrorResponseBuilder.build(errorCode, errorCode.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<List<ErrorDetail>>> handleValidationException(MethodArgumentNotValidException ex) {
        List<ErrorDetail> errors = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> {
                    if (error instanceof FieldError fieldError) {
                        return new ErrorDetail(
                                fieldError.getField(),
                                fieldError.getDefaultMessage(),
                                fieldError.getCode()
                        );
                    }
                    return new ErrorDetail(null, error.getDefaultMessage(), error.getCode());
                }).toList();
        ApiResponse<List<ErrorDetail>> response = new ApiResponse<>();
        response.setResult(errors);
        response.setMessage("Validation failed");
        response.setCode(ErrorCode.INVALID_KEY.getCode());


        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse<List<ErrorDetail>>> handleConstraintViolation(ConstraintViolationException ex) {
        List<ErrorDetail> errors = ex.getConstraintViolations().stream()
                .map(vio ->
                     new ErrorDetail(
                            vio.getPropertyPath().toString(),
                            vio.getMessage(),
                            vio.getConstraintDescriptor().getAnnotation().annotationType().getSimpleName()
                    )
                ).toList();
        ApiResponse<List<ErrorDetail>> response = new ApiResponse<>();
        response.setCode(ErrorCode.INVALID_KEY.getCode());
        response.setMessage("Constraint violation");
        response.setResult(errors);

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<?>> handleJsonParseException(HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest()
                .body(ErrorResponseBuilder.build(ErrorCode.INVALID_JSON_FORMAT,
                        ErrorCode.INVALID_JSON_FORMAT.getMessage() + ex.getMessage()));
    }

}


