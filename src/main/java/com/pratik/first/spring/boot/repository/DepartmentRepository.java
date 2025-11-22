package com.pratik.first.spring.boot.repository;

import com.pratik.first.spring.boot.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>, CustomRepository {
}
