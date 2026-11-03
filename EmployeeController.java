package com.kiet.employee_management;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    List<Employee> employees = new ArrayList<>();

    // 1. POST - Add Employee
    @PostMapping
    public ResponseEntity<?> addEmployee(
            @RequestBody Employee employee) {

        for (Employee e : employees) {
            if (e.getId() == employee.getId()) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Employee ID already exists!");
            }
        }

        employees.add(employee);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Employee added successfully!");
    }

    // 2. GET - Get All Employees
    @GetMapping
    public List<Employee> getAllEmployees() {

        return employees;
    }

    // 3. GET - Get Employee By ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(
            @PathVariable int id) {

        for (Employee e : employees) {
            if (e.getId() == id) {
                return ResponseEntity.ok(e);
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Employee not found!");
    }

    // 4. PUT - Update Employee
    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(
            @PathVariable int id,
            @RequestBody Employee updatedEmployee) {

        for (Employee e : employees) {

            if (e.getId() == id) {

                e.setName(updatedEmployee.getName());
                e.setDepartment(updatedEmployee.getDepartment());
                e.setSalary(updatedEmployee.getSalary());

                return ResponseEntity.ok(e);
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Employee not found!");
    }

    // 5. DELETE - Delete Employee
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(
            @PathVariable int id) {

        for (Employee e : employees) {

            if (e.getId() == id) {

                employees.remove(e);

                return ResponseEntity.ok(
                        "Employee deleted successfully!");
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Employee not found!");
    }
}