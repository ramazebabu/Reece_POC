package com.learn.springreactivecrud.repository;

import com.learn.springreactivecrud.entity.Employee;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends ReactiveMongoRepository<Employee,String> {
}
