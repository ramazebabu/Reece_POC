package com.learn.springreactivecrud.controller;

import com.learn.springreactivecrud.dto.EmployeeDto;
import com.learn.springreactivecrud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    private EmployeeService service;

    @Autowired
    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping("/profile")
    public Flux<EmployeeDto> getAllProfiles(){
        return service.getAllProfiles();
    }

    @GetMapping("/profile/{id}")
    public Mono<EmployeeDto> getProfile(@PathVariable String id){
        return service.getProfile(id);
    }

    @PostMapping("/profile")
    public Mono<EmployeeDto> saveProfile(@RequestBody Mono<EmployeeDto> employeeDtoMono){
        return service.saveProfile(employeeDtoMono);
    }

    @PutMapping("/profile/update/{id}")
    public Mono<EmployeeDto> updateProfile(@RequestBody Mono<EmployeeDto> employeeDtoMono,@PathVariable String id){
        return service.updateProfile(employeeDtoMono,id);
    }

    @DeleteMapping("/profile/delete/{id}")
    public Mono<Void> deleteProfile(@PathVariable String id){
        return service.deleteProfile(id);
    }


}
