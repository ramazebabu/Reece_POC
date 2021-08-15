package com.learn.liquibase.demo.service;

import com.learn.liquibase.demo.exception.UserExistsException;
import com.learn.liquibase.demo.exception.UserNotFoundException;
import com.learn.liquibase.demo.model.employee.Employee;
import com.learn.liquibase.demo.model.student.Student;

import java.util.List;

public interface StudentService {

    List<Student> allProfiles();
    String addProfile(Student student) throws UserExistsException;
    void updateProfile(Student student) throws UserNotFoundException;
    String deleteProfile(String email) throws UserNotFoundException;

}
