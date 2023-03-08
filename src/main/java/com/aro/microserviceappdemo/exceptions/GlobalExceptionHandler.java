package com.aro.microserviceappdemo.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), exception.getMessage(),
                webRequest.getDescription(false), "USER_NOT_FOUND");

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);


    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
        allErrors.forEach((objectError -> {
            String fieldName = ((FieldError) objectError).getField();
            String message = objectError.getDefaultMessage();
            errors.put(fieldName, message);
        }));

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

    }
    }

