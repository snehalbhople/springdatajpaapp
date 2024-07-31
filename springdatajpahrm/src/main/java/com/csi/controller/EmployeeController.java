package com.csi.controller;

import com.csi.exception.RecordNotFoundException;
import com.csi.model.Employee;
import com.csi.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeServiceImpl employeeServiceImpl;

    // im chaitanya
    @PostMapping("/signup")
    public ResponseEntity<Employee> signUp(@RequestBody Employee employee) {
        return ResponseEntity.ok(employeeServiceImpl.signUp(employee));
    }

    @PostMapping("/saveall")
    public ResponseEntity<List<Employee>> saveAll(@RequestBody List<Employee> employeeList) {
        return ResponseEntity.ok(employeeServiceImpl.saveAll(employeeList));
    }

    @GetMapping("/signin/{empEmailId}/{empPassword}")
    public ResponseEntity<Boolean> signIn(@PathVariable String empEmailId, String empPassword) {
        return ResponseEntity.ok(employeeServiceImpl.signIn(empEmailId, empPassword));
    }

    @GetMapping("/findall")
    public ResponseEntity<List<Employee>> findAll() {
        return ResponseEntity.ok(employeeServiceImpl.findAll());
    }

    @GetMapping("/findbyid/{empId}")
    public ResponseEntity<Optional<Employee>> findById(@PathVariable int empId) {
        return ResponseEntity.ok(employeeServiceImpl.findById(empId));
    }

    @GetMapping("/findbyName/{empName}")
    public ResponseEntity<List<Employee>> findByName(@PathVariable String empName) {
        return ResponseEntity.ok(employeeServiceImpl.findByName(empName));
    }

    @GetMapping("/findbycontactnumber/{empContactNumber}")
    public ResponseEntity<Employee> findByContactNumber(@PathVariable long empContactNumber) {
        return ResponseEntity.ok(employeeServiceImpl.findByContactNumber(empContactNumber));
    }

    @GetMapping("/sortbysalary")
    public ResponseEntity<List<Employee>> sortBySalary() {
        return ResponseEntity.ok(employeeServiceImpl.findAll().stream().sorted(Comparator.comparingDouble(Employee::getEmpSalary)).toList());
    }

    @GetMapping("/filterbysalary/{empSalary}")
    public ResponseEntity<List<Employee>> filterBySalary(@PathVariable double empSalary) {
        return ResponseEntity.ok(employeeServiceImpl.findAll().stream().filter(emp -> emp.getEmpSalary() >= empSalary).toList());
    }

    @PutMapping("/update/{empId}")
    public ResponseEntity<Employee> update(@PathVariable long empId, @RequestBody Employee employee) throws RecordNotFoundException {
        Employee employee1 = employeeServiceImpl.findById(empId).orElseThrow(() -> new RecordNotFoundException("Employee Id Doesn't Exist"));
        employee1.setEmpName(employee.getEmpName());
        employee1.setEmpAddress(employee.getEmpAddress());
        employee1.setEmpSalary(employee.getEmpSalary());
        employee1.setEmpContactNumber(employee.getEmpContactNumber());
        employee1.setEmpDOB(employee.getEmpDOB());
        employee1.setEmpEmailId(employee.getEmpEmailId());
        employee1.setEmpPassword(employee.getEmpPassword());

        return ResponseEntity.ok(employeeServiceImpl.update(employee1));
    }

      /*  @DeleteMapping("/deletebyid/{empId}")
        public  ResponseEntity<String> deleteById(@PathVariable long empId){
            employeeServiceImpl.deleteById(empId);

        }*/


        @DeleteMapping("/deletebyid/{empId}")
        public ResponseEntity<String> deleteById(@PathVariable long empId){
            employeeServiceImpl.deleteById(empId);
            return ResponseEntity.ok("Data Deleted Successfully !");
        }

        @DeleteMapping("/deleteall")
    public ResponseEntity<String> deleteAll(){
            employeeServiceImpl.deleteAll();
            return ResponseEntity.ok("All data Deleted Successfully !");
        }

    }



