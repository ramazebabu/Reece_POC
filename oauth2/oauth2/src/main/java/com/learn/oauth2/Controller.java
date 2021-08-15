package com.learn.oauth2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class Controller {

    @GetMapping("/")
    public String hello(){
        return "Hello";
    }

    @GetMapping("/restricted")
    public String loginString(){
        return "You are now logged in !!";
    }

    @GetMapping("/user")
    public String user(Principal principal){
        System.out.println(principal);
        return "User"+" "+principal.getName()+" "+"is"+" "+"logged in";
    }

    @GetMapping("/signOut")
    public String logoutString(){
        return "You are now logged out !!";
    }

}
