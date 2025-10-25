package com.pratik.first.spring.boot.dto;

import lombok.Data;

import java.util.Date;

@Data
public class EmployeeResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Date dateOfJoining;
    private DepartmentResponseDto department;
}

