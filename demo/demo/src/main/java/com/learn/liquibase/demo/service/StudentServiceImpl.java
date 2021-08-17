package com.learn.liquibase.demo.service;

import com.learn.liquibase.demo.exception.UserExistsException;
import com.learn.liquibase.demo.exception.UserNotFoundException;
import com.learn.liquibase.demo.model.student.Student;
import com.learn.liquibase.demo.repository.student.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService{

    private StudentRepo repo;

    @Autowired
    public StudentServiceImpl(StudentRepo repo) {
        this.repo = repo;
    }

    @Override
    public List<Student> allProfiles() {
        return repo.findAll();
    }

    @Override
    public String addProfile(Student student) throws UserExistsException {
        if(repo.existsByEmail(student.getEmail())){
            throw new UserExistsException("Profile Already exists With Email");
        }
        Student savedProfile = repo.save(student);
        return savedProfile.getName();
    }

    @Override
    public void updateProfile(Student student) throws UserNotFoundException {
        Optional<Student> byEmail = repo.findByEmail(student.getEmail());
        if(byEmail.isPresent()){
            Student stud = repo.findByEmail(student.getEmail()).get();
            stud.setName(student.getName());
            stud.setEmail(student.getEmail());
            stud.setDob(student.getDob());
            stud.setGender(student.getGender());
            stud.setGroup(student.getGroup());
            repo.save(stud);
        }
        else{
            throw new UserNotFoundException("Profile Not Found");
        }
    }

    @Override
    public String deleteProfile(String email) throws UserNotFoundException {
        if(repo.existsByEmail(email)){
            String name = repo.deleteByEmail(email);
            return name;
        }
        throw new UserNotFoundException("Profile Not Found");
    }
}
