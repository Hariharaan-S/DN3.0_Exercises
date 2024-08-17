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
//		//code
//
		Department department1 = new Department();
		department1.setName("SWE II");
		Department savedDepartment1 = departmentRepository.save(department1);

		Department department2 = new Department();
		department2.setName("SWE I");
		Department savedDepartment2 = departmentRepository.save(department2);

		Department department3= new Department();
		department3.setName("Sales");
		Department savedDepartment3= departmentRepository.save(department3);

		// Create Employee
		Employee employee = new Employee();
		employee.setName("Hariharaan S");
		employee.setEmail("sample@sample.com");
		employee.setDepartment(savedDepartment1);

		// Create Employee
		Employee employee2 = new Employee();
		employee2.setName("A S");
		employee2.setEmail("sample1@sample.com");
		employee2.setDepartment(savedDepartment1);

		// Create Employee
		Employee employee3 = new Employee();
		employee3.setName("B S");
		employee3.setEmail("sample2@sample.com");
		employee3.setDepartment(savedDepartment2);

		// Create Employee
		Employee employee4 = new Employee();
		employee4.setName("C S");
		employee4.setEmail("sample4@sample.com");
		employee4.setDepartment(savedDepartment2);

		// Create Employee
		Employee employee5 = new Employee();
		employee5.setName("D S");
		employee5.setEmail("sample34@sample.com");
		employee5.setDepartment(savedDepartment1);

		// Create Employee
		Employee employee6 = new Employee();
		employee6.setName("E S");
		employee6.setEmail("sample@sample.com");
		employee6.setDepartment(savedDepartment3);

		// Create Employee
		Employee employee7 = new Employee();
		employee7.setName("F S");
		employee7.setEmail("sample54@sample.com");
		employee7.setDepartment(savedDepartment3);

		// Create Employee
		Employee employee8 = new Employee();
		employee8.setName("O S");
		employee8.setEmail("samplei88@sample.com");
		employee8.setDepartment(savedDepartment3);

		// Create Employee
		Employee employee9 = new Employee();
		employee9.setName("PP S");
		employee9.setEmail("sample432@sample.com");
		employee9.setDepartment(savedDepartment3);

		// Create Employee
		Employee employee10 = new Employee();
		employee10.setName("WEE S");
		employee10.setEmail("sampleEE44@sample.com");
		employee10.setDepartment(savedDepartment1);

		Employee savedEmployee = employeeRepository.save(employee);
		 savedEmployee = employeeRepository.save(employee2);
		 savedEmployee = employeeRepository.save(employee3);
		 savedEmployee = employeeRepository.save(employee4);
		 savedEmployee = employeeRepository.save(employee5);
		 savedEmployee = employeeRepository.save(employee6);
		 savedEmployee = employeeRepository.save(employee7);
		 savedEmployee = employeeRepository.save(employee8);
		 savedEmployee = employeeRepository.save(employee9);
		 savedEmployee = employeeRepository.save(employee10);

	}
}
