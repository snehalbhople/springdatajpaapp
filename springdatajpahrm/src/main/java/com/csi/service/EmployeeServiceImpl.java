package com.csi.service;

import com.csi.model.Employee;
import com.csi.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl {
    @Autowired
    private EmployeeRepository employeeRepositoryImpl;

    public Employee signUp(Employee employee) {
        return employeeRepositoryImpl.save(employee);
    }

    public boolean signIn(String empEmailId, String empPassword) {
        boolean flag = false;

        Employee employee = employeeRepositoryImpl.findByEmpEmailIdAndEmpPassword(empEmailId, empPassword);
        if (employee != null && employee.getEmpEmailId().equals(empEmailId) && employee.getEmpPassword().equals(empPassword)) {
            flag = true;

        }
        return flag;

    }

    public List<Employee> saveAll(List<Employee> employeeList) {
        return employeeRepositoryImpl.saveAll(employeeList);
    }

    public Employee findByContactNumber(long empContactNumber) {
        return employeeRepositoryImpl.findByEmpContactNumber(empContactNumber);
    }

    public Optional<Employee> findById(long empId) {
        return employeeRepositoryImpl.findById(empId);
    }

    public List<Employee> findAll() {
        return employeeRepositoryImpl.findAll();
    }

    public List<Employee> findByName(String empName) {
        return employeeRepositoryImpl.findByEmpName(empName);
    }

    public Employee update(Employee employee) {
        return employeeRepositoryImpl.save(employee);
    }

    public void deleteById(long empID) {
        employeeRepositoryImpl.deleteById(empID);
    }

    public void deleteAll() {
        employeeRepositoryImpl.deleteAll();
    }

}
