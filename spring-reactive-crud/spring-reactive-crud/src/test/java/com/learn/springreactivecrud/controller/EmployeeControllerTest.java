package com.learn.springreactivecrud.controller;

import com.learn.springreactivecrud.dto.EmployeeDto;
import com.learn.springreactivecrud.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebFluxTest(EmployeeController.class)
public class EmployeeControllerTest {

    public static final String COMMON_URL = "/api/v1/employee";

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private EmployeeService service;

    @Test
    public void addProfileTest(){

        Mono<EmployeeDto> employeeDtoMono = Mono.just(new EmployeeDto("1", "Ramesh", "ramesh23@gmail.com", "male", "23/03/1996", "BackendDev"));
        when(service.saveProfile(employeeDtoMono)).thenReturn(employeeDtoMono);
        webTestClient.post().uri(COMMON_URL+"/profile")
                .body(Mono.just(employeeDtoMono),EmployeeDto.class)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void getAllProfilesTest(){

        Flux<EmployeeDto> employeeDtoFlux = Flux.just(new EmployeeDto("1", "Ramesh", "ramesh23@gmail.com", "male", "23/03/1996", "BackendDev"), new EmployeeDto("2", "Swathi", "swathi10@gmail.com", "female", "10/05/1995", "FrontendDev"));
        when(service.getAllProfiles()).thenReturn(employeeDtoFlux);

        Flux<EmployeeDto> responseBody = webTestClient.get().uri(COMMON_URL + "/profile")
                .exchange()
                .expectStatus().isOk()
                .returnResult(EmployeeDto.class)
                .getResponseBody();

        StepVerifier.create(responseBody)
                .expectSubscription()
                .expectNext(new EmployeeDto("1", "Ramesh", "ramesh23@gmail.com", "male", "23/03/1996", "BackendDev"))
                .expectNext(new EmployeeDto("2", "Swathi", "swathi10@gmail.com", "female", "10/05/1995", "FrontendDev"))
                .verifyComplete();

    }

    @Test
    public void getProfileByIdTest(){

        Mono<EmployeeDto> employeeDtoMono = Mono.just(new EmployeeDto("1", "Ramesh", "ramesh23@gmail.com", "male", "23/03/1996", "BackendDev"));
        when(service.getProfile(any())).thenReturn(employeeDtoMono);

        Flux<EmployeeDto> responseBody = webTestClient.get().uri(COMMON_URL + "/profile/1")
                .exchange()
                .expectStatus().isOk()
                .returnResult(EmployeeDto.class)
                .getResponseBody();

        StepVerifier.create(responseBody)
                .expectSubscription()
                .expectNextMatches(e->e.getEmail().equalsIgnoreCase("ramesh23@gmail.com"))
                .verifyComplete();
    }

    @Test
    public void updateProfileByIdTest(){
        Mono<EmployeeDto> employeeDtoMono = Mono.just(new EmployeeDto("2", "Swathi", "swathi10@gmail.com", "female", "10/05/1995", "FrontendDev"));
        when(service.updateProfile(employeeDtoMono,"2")).thenReturn(employeeDtoMono);

        webTestClient.put().uri(COMMON_URL+"/profile/update/2")
                .exchange()
                .expectStatus().isOk();

    }

    @Test
    public void deleteProfileByIdTest(){

        given(service.deleteProfile(any())).willReturn(Mono.empty());

        webTestClient.delete().uri(COMMON_URL+"/profile/delete/2")
                .exchange()
                .expectStatus().isOk();

    }

}
