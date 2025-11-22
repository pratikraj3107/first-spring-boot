package com.pratik.first.spring.boot.repository;

import com.pratik.first.spring.boot.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>, CustomRepository {
}
