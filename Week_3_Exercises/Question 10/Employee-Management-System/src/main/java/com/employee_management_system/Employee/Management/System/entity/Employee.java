package com.employee_management_system.Employee.Management.System.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "employees")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Employee extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Employee ID")
    private int id;
    private String name;
    private String email;
    @ManyToOne
    @JoinColumn(name = "department_id")
    @BatchSize(size = 10)
    private Department department;
}
