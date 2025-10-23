package com.pratik.first.spring.boot.exception;


import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class EmployeeException extends RuntimeException {

    private final HttpStatus status;
    public EmployeeException(String message) {
        this(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    public EmployeeException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
