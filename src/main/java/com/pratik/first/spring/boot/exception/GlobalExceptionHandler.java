package com.pratik.first.spring.boot.exception;

import com.pratik.first.spring.boot.dto.ErrorDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmployeeException.class)
    public ResponseEntity<ErrorDto> handleEmployeeException(EmployeeException ex) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setStatus("got exception");
        errorDto.setMessage(ex.getMessage());
        return new ResponseEntity<>(errorDto, ex.getStatus());
    }
}
