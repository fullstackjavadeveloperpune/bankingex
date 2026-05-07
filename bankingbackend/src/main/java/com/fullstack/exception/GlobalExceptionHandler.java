package com.fullstack.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<?> handleRecordNotFoundException(RecordNotFoundException exception) {
        return new ResponseEntity<>("errors{}: " + exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<?> handleInvalidAmountException(InvalidAmountException exception) {
        return new ResponseEntity<>("errors{}: " + exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<?> handleInsufficientFundException(InsufficientFundException exception) {
        return new ResponseEntity<>("errors{}: " + exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<?> handleInvalidOTPException(InvalidOTPException exception) {
        return new ResponseEntity<>("errors{}: " + exception.getMessage(), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handleCustomValidation(MethodArgumentNotValidException exception) {
        Map<String, String> errorMap = new LinkedHashMap<>();

        exception.getBindingResult().getAllErrors().forEach(objectError -> {
            String field = ((FieldError) objectError).getField();
            String fieldMessage = objectError.getDefaultMessage();
            errorMap.put(field, fieldMessage);

        });

        return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
    }
}
