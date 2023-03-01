package com.technicalAssessment.employeeApplication.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Users")
public class User {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;
    @Id
    private String userName;
    private String password;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
