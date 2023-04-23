package com.csi.controller;

import com.csi.model.Employee;
import com.csi.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    EmployeeService employeeServiceImpl;

    @PostMapping("/savedata")

    public ResponseEntity<String>  saveData(@RequestBody Employee employee){
        employeeServiceImpl.saveData(employee);
        return ResponseEntity.ok("Data Saved Successfully");
    }

    @GetMapping("/getalldata")
    public ResponseEntity<List<Employee>>  getAllData() {
       return ResponseEntity.ok( employeeServiceImpl.getAllData());

    }
    @PutMapping("/updatedata/{empId}")
        public ResponseEntity<String> updateData(@PathVariable int empId,@RequestBody Employee employee ){
        employeeServiceImpl.updateData(empId,employee);
        return  ResponseEntity.ok("Data updated Successfully");
        }

        @DeleteMapping("/deletedatabyid/{empId}")

        public ResponseEntity<String> updateData(@PathVariable int empId){
            employeeServiceImpl.deleteDataById(empId);
            return  ResponseEntity.ok("Data deleted Successfully");
        }
    }

