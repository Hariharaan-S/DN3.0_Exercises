package com.employee_management_system.Employee.Management.System.controller;

import com.employee_management_system.Employee.Management.System.entity.Employee;
import com.employee_management_system.Employee.Management.System.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping(value = "/employee")
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee newEmployee){
        try{
            employeeService.saveEmployee(newEmployee);
            return new ResponseEntity<>(HttpStatusCode.valueOf(200));
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatusCode.valueOf(500));
        }
    }

    @GetMapping(value = "/employees")
    public ResponseEntity<List<Employee>> getAllEmployees(){
        try {
            return new ResponseEntity<>(employeeService.getEmployees(), HttpStatusCode.valueOf(200));
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatusCode.valueOf(500));
        }
    }

    @PutMapping(value = "/employee")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee){
        try{
            return new ResponseEntity<>(employeeService.updateEmployee(employee), HttpStatusCode.valueOf(200));
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatusCode.valueOf(500));
        }
    }

    @DeleteMapping(value = "/employee")
    public ResponseEntity<HttpStatusCode> deleteEmployee(@RequestParam(value = "empId") Integer empId ){

        try{
            employeeService.deleteEmployee(empId);
            return new ResponseEntity<>(HttpStatusCode.valueOf(200));
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatusCode.valueOf(500));
        }
    }
}


