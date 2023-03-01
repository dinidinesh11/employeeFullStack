package com.technicalAssessment.employeeApplication.service;

import com.technicalAssessment.employeeApplication.model.Employee;

import java.text.ParseException;
import java.util.List;

public interface EmployeeService {

    Employee createEmployee(Employee employee) throws ParseException;
    Employee updateEmployee(Employee employee) throws ParseException;
    List<Employee> getAllEmployee();
    Employee getEmployeeById(long employeeId);
    void deleteEmployee(long employeeId);


}
