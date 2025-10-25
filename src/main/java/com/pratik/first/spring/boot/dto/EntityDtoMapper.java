package com.pratik.first.spring.boot.dto;

import com.pratik.first.spring.boot.model.Department;
import com.pratik.first.spring.boot.model.Employee;

import java.util.List;
import java.util.stream.Collectors;

public class EntityDtoMapper {

    public static DepartmentResponseDto toDepartmentResponseDto(Department department) {
        if (department == null) return null;
        DepartmentResponseDto dto = new DepartmentResponseDto();
        dto.setId(department.getId());
        dto.setName(department.getName());
        return dto;
    }

    public static EmployeeResponseDto toEmployeeResponseDto(Employee employee) {
        if (employee == null) return null;
        EmployeeResponseDto dto = new EmployeeResponseDto();
        dto.setId(employee.getId());
        dto.setFirstName(employee.getFirstName());
        dto.setLastName(employee.getLastName());
        dto.setEmail(employee.getEmail());
        dto.setDateOfJoining(employee.getDateOfJoining());
        dto.setDepartment(toDepartmentResponseDto(employee.getDepartment()));
        return dto;
    }

    public static List<EmployeeResponseDto> toEmployeeResponseDtoList(List<Employee> employees) {
        return employees.stream().map(EntityDtoMapper::toEmployeeResponseDto).collect(Collectors.toList());
    }

    public static List<DepartmentResponseDto> toDepartmentResponseDtoList(List<Department> departments) {
        return departments.stream().map(EntityDtoMapper::toDepartmentResponseDto).collect(Collectors.toList());
    }
}

