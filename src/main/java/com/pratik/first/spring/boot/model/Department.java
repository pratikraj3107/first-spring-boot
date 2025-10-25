package com.pratik.first.spring.boot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "department")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    @EqualsAndHashCode.Include
    private Long id;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "department")
    @JsonIgnore
    @ToString.Exclude
    private List<Employee> employees;

}
