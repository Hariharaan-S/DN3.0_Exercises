package com.employee_management_system.Employee.Management.System.repository;

import com.employee_management_system.Employee.Management.System.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
