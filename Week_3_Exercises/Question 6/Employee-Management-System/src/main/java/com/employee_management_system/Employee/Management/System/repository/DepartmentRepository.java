package com.employee_management_system.Employee.Management.System.repository;

import com.employee_management_system.Employee.Management.System.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
//    Department findById(Integer id);

    @Query(name = "findUsingID")
    Department findByName(String name);
}
