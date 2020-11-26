package com.wolox.technicalTest.errors;

import com.wolox.technicalTest.constants.ErrorConstants;
import com.wolox.technicalTest.exceptions.IncorrectQueryParamsException;
import com.wolox.technicalTest.models.dtos.RequestResponses.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
import java.util.Objects;

@ControllerAdvice
public class ControllerErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> validationErrorHandler(MethodArgumentNotValidException exception) {

        ErrorResponseDto detalleError = ErrorResponseDto.builder()
                .timestamp(new Date())
                .error(ErrorConstants.OBJECT_FIELDS_ERROR)
                .detail(Objects.requireNonNull(exception.getBindingResult().getFieldError()).getDefaultMessage())
                .build();
        return new ResponseEntity<>(detalleError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IncorrectQueryParamsException.class)
    public ResponseEntity<?> incorrectQueryParamsErrorHandler(IncorrectQueryParamsException exception) {
        ErrorResponseDto detalleError = ErrorResponseDto.builder()
                .timestamp(new Date())
                .error(ErrorConstants.QUERY_PARAMS_ERROR)
                .detail(Objects.requireNonNull(exception.getMessage()))
                .build();
        return new ResponseEntity<>(detalleError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> incorrectQueryParamsErrorHandler(Exception exception) {
        ErrorResponseDto detalleError = ErrorResponseDto.builder()
                .timestamp(new Date())
                .error(ErrorConstants.GENERIC_ERROR)
                .detail(Objects.requireNonNull(exception.getMessage()))
                .build();
        return new ResponseEntity<>(detalleError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
