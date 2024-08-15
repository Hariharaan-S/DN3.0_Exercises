package com.employee_management_system.Employee.Management.System.repository;

import com.employee_management_system.Employee.Management.System.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
