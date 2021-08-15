package com.learn.liquibase.demo.service;

import com.learn.liquibase.demo.exception.UserExistsException;
import com.learn.liquibase.demo.exception.UserNotFoundException;
import com.learn.liquibase.demo.model.employee.Employee;
import java.util.List;

public interface EmployeeService {

    List<Employee> allProfiles();
    String addProfile(Employee employee) throws UserExistsException;
    void updateProfile(Employee employee) throws UserNotFoundException;
    String deleteProfile(String email) throws UserNotFoundException;

}
