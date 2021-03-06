package com.cg.emppayrollapp.controller;

import com.cg.emppayrollapp.dto.EmployeePayrollDto;
import com.cg.emppayrollapp.exceptions.PayrollException;
import com.cg.emppayrollapp.exceptions.UserNotFound;
import com.cg.emppayrollapp.service.EmployeePayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class UserController {

    @Autowired
    EmployeePayrollService employeePayrollService;
    //http:localhost/employee-payroll/create (dto)
    @PostMapping("/create")
    public ResponseEntity<EmployeePayrollDto> createUser(@RequestBody EmployeePayrollDto user){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(employeePayrollService.CreateUser(user));
        } catch (UserNotFound e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    //http:localhost/employee-payroll/update (dto)
    @PutMapping("/update")
    public ResponseEntity<EmployeePayrollDto> updateUser(@RequestBody EmployeePayrollDto user){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(employeePayrollService.UpdateUser(user));
        } catch (UserNotFound e){
            throw new PayrollException(PayrollException.ExceptionTypes.EMPLOYEE_NOT_FOUND);
        }
    }

    //http://localhost:8080/employee-payroll/delete/1
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<EmployeePayrollDto> deleteUser(@PathVariable("id")Long id){
        try{
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(employeePayrollService.deleteUser(id));
        } catch (UserNotFound e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    //http://localhost:8080/employee-payroll/get
    @GetMapping("/get")
    public ResponseEntity<List<EmployeePayrollDto>> getAllUser(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(employeePayrollService.getAllUser());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
