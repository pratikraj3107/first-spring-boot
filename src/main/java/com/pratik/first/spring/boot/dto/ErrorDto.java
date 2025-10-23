package com.pratik.first.spring.boot.dto;

import lombok.Data;

@Data
public class ErrorDto {
    private String status;
    private String message;
}
