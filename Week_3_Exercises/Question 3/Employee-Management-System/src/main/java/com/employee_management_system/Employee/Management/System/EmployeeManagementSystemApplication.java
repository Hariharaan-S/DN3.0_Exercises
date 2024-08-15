package com.employee_management_system.Employee.Management.System;

import com.employee_management_system.Employee.Management.System.entity.Department;
import com.employee_management_system.Employee.Management.System.entity.Employee;
import com.employee_management_system.Employee.Management.System.repository.DepartmentRepository;
import com.employee_management_system.Employee.Management.System.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmployeeManagementSystemApplication implements CommandLineRunner {
	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private DepartmentRepository departmentRepository;
	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagementSystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//code

		Department department = new Department();
		department.setName("HR");

		Department savedDepartment = departmentRepository.save(department);

		// Create Employee
		Employee employee = new Employee();
		employee.setName("Hariharaan S");
		employee.setEmail("sample@sample.com");
		employee.setDepartment(savedDepartment);

		// Save Employee
		Employee savedEmployee = employeeRepository.save(employee);

		// Display Employee ID
		System.out.println("Employee ID: " + savedEmployee.getId());

	}
}
