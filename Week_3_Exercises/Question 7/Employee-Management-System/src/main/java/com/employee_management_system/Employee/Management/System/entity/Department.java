package com.employee_management_system.Employee.Management.System.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@NamedQuery(name = "findUsingID", query = "select d from Department d where d.name = ?1")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @OneToMany
    @JoinColumn(name = "employee_id")
    private List<Employee>employeeList;
}
