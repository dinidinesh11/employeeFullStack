package com.technicalAssessment.employeeApplication.controller;

import com.technicalAssessment.employeeApplication.model.Employee;
import com.technicalAssessment.employeeApplication.service.EmployeeService;
import com.technicalAssessment.employeeApplication.utils.JwtUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@Validated
@CrossOrigin("http://localhost:3000")
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    Validator validator;

    @GetMapping("/allDetails")
    public ResponseEntity<List<Employee>> getAllEmployee(@RequestHeader(value = "authorization", defaultValue = "")String auth) throws Exception {
       try {
           jwtUtils.verify(auth);
           return ResponseEntity.ok(employeeService.getAllEmployee());
       } catch (Exception e){
           return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
       }
    }
    @GetMapping("/getOne/{id}")
    @Cacheable(key = "#id", value = "Employee")
    public ResponseEntity<Employee> getEmployeeById(@RequestHeader(value = "authorization", defaultValue = "")String auth,
                                                    @PathVariable long id) throws Exception {
        try {

            jwtUtils.verify(auth);
            return ResponseEntity.ok(employeeService.getEmployeeById(id));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    @PostMapping("/save")
    public ResponseEntity<Employee> createEmployee(@Valid @RequestHeader(value = "authorization", defaultValue = "")String auth,
                                                   @RequestBody Employee employee) throws Exception {
        //try {
            jwtUtils.verify(auth);

            return new ResponseEntity<>(employeeService.createEmployee(employee), HttpStatus.CREATED);

      //  } catch (Exception e){
        //   return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

     //   }
    }
    @PutMapping("/update/{id}")
    @CachePut(key = "#id", value = "Employee")
    public ResponseEntity<Employee> updateEmployee(@RequestHeader(value = "authorization", defaultValue = "")String auth,
                                                   @PathVariable long id, @RequestBody Employee employee) throws Exception {
        try {
            jwtUtils.verify(auth);
            employee.setEmployeeId(id);
            return ResponseEntity.ok(employeeService.updateEmployee(employee));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
       }
    }
    @DeleteMapping("/delete/{id}")
    @CacheEvict(key = "#id", value = "Employee")
    public ResponseEntity<?> deleteEmployee(@RequestHeader(value = "authorization", defaultValue = "")String auth,
                                     @PathVariable long id) throws Exception {

        try {
            jwtUtils.verify(auth);
            this.employeeService.deleteEmployee(id);
            return ResponseEntity.ok("employee deleted");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
