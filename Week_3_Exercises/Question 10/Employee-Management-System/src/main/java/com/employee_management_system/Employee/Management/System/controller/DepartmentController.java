package com.employee_management_system.Employee.Management.System.controller;

import com.employee_management_system.Employee.Management.System.entity.Department;
import com.employee_management_system.Employee.Management.System.entity.Employee;
import com.employee_management_system.Employee.Management.System.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;


    @PostMapping(value = "/department")
    public ResponseEntity<Department> saveEmployee(@RequestBody Department newDepartment){
        try{
            departmentService.saveDepartment(newDepartment);
            return new ResponseEntity<>(HttpStatusCode.valueOf(200));
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatusCode.valueOf(500));
        }
    }

    @GetMapping(value = "/departments")
    public ResponseEntity<List<Department>> getAllEmployees(){
        try {
            return new ResponseEntity<>(departmentService.getDepartments(), HttpStatusCode.valueOf(200));
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatusCode.valueOf(500));
        }
    }

    @PutMapping(value = "/department")
    public ResponseEntity<Department> updateEmployee(@RequestBody Department department){
        try{
            return new ResponseEntity<>(departmentService.updateDepartment(department), HttpStatusCode.valueOf(200));
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatusCode.valueOf(500));
        }
    }

    @DeleteMapping(value = "/department")
    public ResponseEntity<HttpStatusCode> deleteEmployee(@RequestParam(value = "deptId") Integer deptId ){

        try{
            departmentService.deleteDepartment(deptId);
            return new ResponseEntity<>(HttpStatusCode.valueOf(200));
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatusCode.valueOf(500));
        }
    }
}
