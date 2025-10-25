package com.pratik.first.spring.boot.controller;

import com.pratik.first.spring.boot.dto.DepartmentRequestDto;
import com.pratik.first.spring.boot.dto.DepartmentResponseDto;
import com.pratik.first.spring.boot.dto.EntityDtoMapper;
import com.pratik.first.spring.boot.model.Department;
import com.pratik.first.spring.boot.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Departments")
public class DepartmentController {

    private final DepartmentService DepartmentService;

    public DepartmentController(DepartmentService DepartmentService) {
        this.DepartmentService = DepartmentService;
    }

    @PostMapping(value = "/create", consumes = "application/json")
    public ResponseEntity<DepartmentResponseDto> createDepartment(@RequestBody DepartmentRequestDto departmentRequestDto) {
        System.out.println("Received Department DTO: " + departmentRequestDto);
        Department savedDepartment = DepartmentService.saveDepartment(departmentRequestDto);
        return ResponseEntity.ok(EntityDtoMapper.toDepartmentResponseDto(savedDepartment));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<DepartmentResponseDto> getDepartmentById(@PathVariable Long id) {
        Department department = DepartmentService.getDepartmentById(id);
        return new ResponseEntity<>(EntityDtoMapper.toDepartmentResponseDto(department), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<DepartmentResponseDto> updateDepartment(@PathVariable Long id, @RequestBody DepartmentRequestDto departmentRequestDto) {
        Department updatedDepartment = DepartmentService.updateDepartment(id, departmentRequestDto);
        return new ResponseEntity<>(EntityDtoMapper.toDepartmentResponseDto(updatedDepartment), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
        DepartmentService.deleteDepartment(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<DepartmentResponseDto>> getAllDepartmentBy() {
        List<Department> Departments = DepartmentService.getAllDepartment();
        return new ResponseEntity<>(EntityDtoMapper.toDepartmentResponseDtoList(Departments), HttpStatus.OK);
    }
}