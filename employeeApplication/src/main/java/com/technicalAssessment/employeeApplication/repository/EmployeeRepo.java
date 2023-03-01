package com.technicalAssessment.employeeApplication.repository;

import com.technicalAssessment.employeeApplication.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {
Employee findByEmail(String email);
}
