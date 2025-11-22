package com.pratik.first.spring.boot.service;

import com.pratik.first.spring.boot.dto.EmployeeRequestDto;
import com.pratik.first.spring.boot.exception.EmployeeException;
import com.pratik.first.spring.boot.model.Employee;
import com.pratik.first.spring.boot.model.Department;
import com.pratik.first.spring.boot.repository.EmployeeRepository;
import com.pratik.first.spring.boot.repository.DepartmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public EmployeeService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    public Employee saveEmployee(EmployeeRequestDto employeeDto) {
        Employee employee = getEmployeeFromDto(employeeDto);
        return employeeRepository.save(employee);
    }

    private Employee getEmployeeFromDto(EmployeeRequestDto employeeDto) {
        Employee employee = new Employee();
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());
        employee.setDateOfJoining(employeeDto.getDateOfJoining());
        if (employeeDto.getDepartmentId() != null) {
            Long deptId = employeeDto.getDepartmentId();
            Department department = departmentRepository.findById(deptId)
                    .orElseThrow(() -> new EmployeeException("department-not-found id=" + deptId, HttpStatus.NOT_FOUND));
            employee.setDepartment(department);
        }
        return employee;
    }

    public Employee getEmployeeById(Long id) {
        log.info("Retrieving employee with ID: {}", id);
        return employeeRepository.findById(id).orElseThrow(() -> new EmployeeException("employee-not-found id=" + id, HttpStatus.NOT_FOUND));
    }

    public Employee updateEmployee(Long id, EmployeeRequestDto employeeDto) {
        Employee existingEmployee = getEmployeeById(id);
        existingEmployee.setFirstName(employeeDto.getFirstName());
        existingEmployee.setLastName(employeeDto.getLastName());
        existingEmployee.setEmail(employeeDto.getEmail());
        existingEmployee.setDateOfJoining(employeeDto.getDateOfJoining());
        if (employeeDto.getDepartmentId() != null) {
            Long deptId = employeeDto.getDepartmentId();
            Department department = departmentRepository.findById(deptId)
                    .orElseThrow(() -> new EmployeeException("department-not-found id=" + deptId, HttpStatus.NOT_FOUND));
            existingEmployee.setDepartment(department);
        }
        return employeeRepository.save(existingEmployee);
    }

    public void deleteEmployee(Long id) {
        Employee existingEmployee = getEmployeeById(id);
        employeeRepository.delete(existingEmployee);
    }

    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    public List<Employee> getEntityByCustomCriteria(Map<String, Object> criteria) {
        log.info("Fetching employee with criteria: {}", criteria);
        List<Employee>empList =  employeeRepository.getEntityWithCustomCriteria(criteria, Employee.class);
        log.info("Found employees: {}", empList);
        return empList;
    }
}
