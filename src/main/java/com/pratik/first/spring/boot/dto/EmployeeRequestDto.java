package com.pratik.first.spring.boot.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

import java.util.Date;

@Data
public class EmployeeRequestDto {

    private Long id;

    @NotBlank(message = "firstName is required")
    private String firstName;

    @NotBlank(message = "lastName is required")
    private String lastName;

    @Email(message = "invalid email")
    private String email;

    @PastOrPresent(message = "dateOfJoining cannot be in the future")
    private Date dateOfJoining;

    private Long departmentId;
}

