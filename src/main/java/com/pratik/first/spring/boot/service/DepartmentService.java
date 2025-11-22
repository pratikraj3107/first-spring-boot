package com.pratik.first.spring.boot.service;

import com.pratik.first.spring.boot.dto.DepartmentRequestDto;
import com.pratik.first.spring.boot.exception.EmployeeException;
import com.pratik.first.spring.boot.model.Department;
import com.pratik.first.spring.boot.model.Employee;
import com.pratik.first.spring.boot.repository.DepartmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Department saveDepartment(DepartmentRequestDto departmentRequestDto) {
        Department department = getDepartmentFromDto(departmentRequestDto);
        return departmentRepository.save(department);
    }

    private Department getDepartmentFromDto(DepartmentRequestDto departmentRequestDto) {
        Department department = new Department();
        department.setName(departmentRequestDto.getName());
        return department;
    }

    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id).orElseThrow(() -> new EmployeeException("not-found", HttpStatus.NOT_FOUND));
    }

    public Department updateDepartment(Long id, DepartmentRequestDto departmentRequestDto) {
        Department existingDepartment = getDepartmentById(id);
        existingDepartment.setName(departmentRequestDto.getName());
        return departmentRepository.save(existingDepartment);
    }

    public void deleteDepartment(Long id) {
        Department existingDepartment = getDepartmentById(id);
        departmentRepository.delete(existingDepartment);
    }

    public List<Department> getAllDepartment() {
        return departmentRepository.findAll();
    }

    public List<Department> getEntityWithCustomCriteria(Map<String, Object> criteria) {
        log.info("Fetching employee with criteria: {}", criteria);
        List<Department>departmentList =  departmentRepository.getEntityWithCustomCriteria(criteria, Department.class);
        log.info("Found employees: {}", departmentList);
        return departmentList;
    }
}
