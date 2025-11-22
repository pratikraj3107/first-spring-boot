package com.pratik.first.spring.boot.controller;

import com.pratik.first.spring.boot.dto.EmployeeRequestDto;
import com.pratik.first.spring.boot.dto.EmployeeResponseDto;
import com.pratik.first.spring.boot.dto.EntityDtoMapper;
import com.pratik.first.spring.boot.model.Employee;
import com.pratik.first.spring.boot.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/employees")
@Slf4j
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping(value="/create", consumes = "application/json")
    public ResponseEntity<EmployeeResponseDto> createEmployee(@Valid @RequestBody EmployeeRequestDto employeeDto) {
        System.out.println("Received Employee DTO: " + employeeDto);
        Employee savedEmployee = employeeService.saveEmployee(employeeDto);
        EmployeeResponseDto response = EntityDtoMapper.toEmployeeResponseDto(savedEmployee);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<EmployeeResponseDto> getEmployeeById(@PathVariable Long id) {
//        log.info("Fetching employee with ID: {}", id);
//        Employee employee = employeeService.getEmployeeById(id);
        Map<String, Object> criteria = Map.of("id", id);
        List<Employee> employee1 = employeeService.getEntityByCustomCriteria(criteria);
        return new ResponseEntity<>(EntityDtoMapper.toEmployeeResponseDto(employee1.getFirst()), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<EmployeeResponseDto> updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeRequestDto employeeDto) {
        Employee updatedEmployee = employeeService.updateEmployee(id, employeeDto);
        return new ResponseEntity<>(EntityDtoMapper.toEmployeeResponseDto(updatedEmployee), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<EmployeeResponseDto>> getAllEmployeeBy() {
        List<Employee> employees = employeeService.getAllEmployee();
        return new ResponseEntity<>(EntityDtoMapper.toEmployeeResponseDtoList(employees), HttpStatus.OK);
    }
}
