package com.employee_management_system.Employee.Management.System.service;

import com.employee_management_system.Employee.Management.System.dto.EmployeeDTO;
import com.employee_management_system.Employee.Management.System.entity.Employee;
import com.employee_management_system.Employee.Management.System.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee saveEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    public List<Employee> getEmployees(){
        return employeeRepository.findAll();
    }

    public Employee updateEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Integer id){
        employeeRepository.deleteById(id);
    }

    public Page<Employee> getEmployeesPagination(EmployeeDTO employeeDTO){
        Pageable pageable =  new EmployeeDTO().getPagable(employeeDTO);
        return employeeRepository.findAll(pageable);
    }
}
