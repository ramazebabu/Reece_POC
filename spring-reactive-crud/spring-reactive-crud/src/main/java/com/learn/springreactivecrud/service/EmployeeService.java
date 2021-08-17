package com.learn.springreactivecrud.service;

import com.learn.springreactivecrud.dto.EmployeeDto;
import com.learn.springreactivecrud.entity.Employee;
import com.learn.springreactivecrud.repository.EmployeeRepo;
import com.learn.springreactivecrud.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EmployeeService {

    private EmployeeRepo repo;

    @Autowired
    public EmployeeService(EmployeeRepo repo) {
        this.repo = repo;
    }

    public Flux<EmployeeDto> getAllProfiles(){
        return repo.findAll().map(AppUtils::entityToDto);
    }

    public Mono<EmployeeDto> getProfile(String id){
        return repo.findById(id).map(AppUtils::entityToDto);
    }

    public Mono<EmployeeDto> saveProfile(Mono<EmployeeDto> employeeDtoMono){
        return employeeDtoMono.map(AppUtils::dtoToEntity)
                .flatMap(repo::insert)
                .map(AppUtils::entityToDto);
    }

    public Mono<EmployeeDto> updateProfile(Mono<EmployeeDto> employeeDtoMono,String id){
        return repo.findById(id)
                .flatMap(e->employeeDtoMono.map(AppUtils::dtoToEntity)
                        .doOnNext(emp->emp.setId(id)))
                .flatMap(repo::save)
                .map(AppUtils::entityToDto);
    }

    public Mono<Void> deleteProfile(String id){
        return repo.deleteById(id);
    }


}
