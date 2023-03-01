package com.technicalAssessment.employeeApplication.controller;

import com.technicalAssessment.employeeApplication.model.User;
import com.technicalAssessment.employeeApplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/User")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/signup")
    public ResponseEntity<User> createUser(@RequestBody User user){
    return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
}
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        try{
        return new ResponseEntity<>(userService.loginUser(user), HttpStatus.OK);
    } catch (Exception ex){
            return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
