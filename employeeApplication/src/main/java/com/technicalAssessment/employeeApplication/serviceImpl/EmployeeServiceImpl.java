package com.technicalAssessment.employeeApplication.serviceImpl;

import com.technicalAssessment.employeeApplication.exception.EmployeeNotFoundException;
import com.technicalAssessment.employeeApplication.model.Employee;
import com.technicalAssessment.employeeApplication.repository.EmployeeRepo;
import com.technicalAssessment.employeeApplication.repository.SkillLevelRepo;
import com.technicalAssessment.employeeApplication.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
     SkillLevelRepo skillLevelRepo;
    @Override
    public Employee createEmployee(Employee employee) throws IllegalStateException, ParseException {

        Employee optionalEmployee = employeeRepo.findByEmail(employee.getEmail());
        if (optionalEmployee == null){
            String str = employee.getDob().substring(0,10);
            employee.setDob(str);
            String str1 = employee.getSelectedSkill();
            str1 = str1.substring(str1.length()-1);
            employee.setSelectedSkill(str1);
            employee.setActive(true);
            return employeeRepo.save(employee);

        }
        else {
            throw  new IllegalStateException("Email already Exist");
        }
    }

    @Override
    public Employee updateEmployee(Employee employee)  {
        Optional<Employee> employeeDb = employeeRepo.findById(employee.getEmployeeId());
        if(employeeDb.isPresent()){
            Employee employeeUpdate = employeeDb.get();
            employeeUpdate.setEmployeeId(employee.getEmployeeId());
            employeeUpdate.setFirstName(employee.getFirstName());
            employeeUpdate.setLastName(employee.getLastName());
            employeeUpdate.setEmail(employee.getEmail());
            employeeUpdate.setAge(employee.getAge());
            employeeUpdate.setDob((employee.getDob()));

         employeeUpdate.setSelectedSkill(employee.getSelectedSkill());
            employeeUpdate.setActive(employee.isActive());
            employeeRepo.save(employeeUpdate);
            return employeeUpdate;
        } else {
            throw new  EmployeeNotFoundException ("No Employee details found with id: " +employee.getEmployeeId());
        }
    }

    @Override
    public List<Employee> getAllEmployee() {
        return this.employeeRepo.findAll();
    }

    @Override
    public Employee getEmployeeById(long employeeId) {
        Optional<Employee> employeeDb = this.employeeRepo.findById(employeeId);
        if (employeeDb.isPresent()) {
            System.out.println("printed from db");
            return employeeDb.get();
        }else {
            throw new EmployeeNotFoundException("No Employee details found with id: " +employeeId);
        }

    }

    @Override
    public void deleteEmployee(long employeeId) {
        Optional<Employee> employeeDb = this.employeeRepo.findById(employeeId);
        if (employeeDb.isPresent()) {
            this.employeeRepo.delete(employeeDb.get());
        }else {
            throw new EmployeeNotFoundException("No Employee details found with id: " +employeeId);
        }


    }
}
