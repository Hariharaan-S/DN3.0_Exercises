package com.employee_management_system.Employee.Management.System.service;

import com.employee_management_system.Employee.Management.System.entity.Department;
import com.employee_management_system.Employee.Management.System.entity.Employee;
import com.employee_management_system.Employee.Management.System.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    public Department saveDepartment(Department department){
        return departmentRepository.save(department);
    }

    public List<Department> getDepartments(){
        return departmentRepository.findAll();
    }

    public Department updateDepartment(Department department){
        return departmentRepository.save(department);
    }

    public void deleteDepartment(Integer id){
        departmentRepository.deleteById(id);
    }
}
