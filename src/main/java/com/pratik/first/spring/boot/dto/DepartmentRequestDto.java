package com.pratik.first.spring.boot.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DepartmentRequestDto {
    private Long departmentId;
    @NotBlank(message = "departmentName is required")
    private String name;
}
