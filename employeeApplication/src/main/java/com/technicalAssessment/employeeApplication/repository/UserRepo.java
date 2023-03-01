package com.technicalAssessment.employeeApplication.repository;

import com.technicalAssessment.employeeApplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, String> {

    User findOneByUserNameAndPassword(String userName, String password);
}
