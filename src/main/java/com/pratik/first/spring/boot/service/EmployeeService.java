package com.pratik.first.spring.boot.service;

import com.pratik.first.spring.boot.dto.EmployeeDto;
import com.pratik.first.spring.boot.exception.EmployeeException;
import com.pratik.first.spring.boot.model.Employee;
import com.pratik.first.spring.boot.reposiotory.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee saveEmployee(EmployeeDto employeeDto) {
        Employee employee = getEmployeeFromDto(employeeDto);
        return employeeRepository.save(employee);
    }

    private Employee getEmployeeFromDto(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());
        employee.setDateOfJoining(employeeDto.getDateOfJoining());
        return employee;
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new EmployeeException("not-found", HttpStatus.NOT_FOUND));
    }

    public Employee updateEmployee(Long id, EmployeeDto employeeDto) {
        Employee existingEmployee = getEmployeeById(id);
        existingEmployee.setFirstName(employeeDto.getFirstName());
        existingEmployee.setLastName(employeeDto.getLastName());
        existingEmployee.setEmail(employeeDto.getEmail());
        existingEmployee.setDateOfJoining(employeeDto.getDateOfJoining());
        return employeeRepository.save(existingEmployee);
    }

    public void deleteEmployee(Long id) {
        Employee existingEmployee = getEmployeeById(id);
        employeeRepository.delete(existingEmployee);
    }

    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }
}
