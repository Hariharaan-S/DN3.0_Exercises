package com.employee_management_system.Employee.Management.System.repository;

import com.employee_management_system.Employee.Management.System.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee findByEmail(String email);
    @Query("select u from Employee u where u.name = ?1")
    Employee findByName(String name);
}
