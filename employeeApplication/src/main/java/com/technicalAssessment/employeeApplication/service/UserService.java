package com.technicalAssessment.employeeApplication.service;

import com.technicalAssessment.employeeApplication.model.User;

public interface UserService {
    User createUser(User user);
    Object loginUser(User user);


}
