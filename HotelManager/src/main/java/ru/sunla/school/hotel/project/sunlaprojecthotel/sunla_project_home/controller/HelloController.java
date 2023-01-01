package ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class HelloController {

    @GetMapping("/hello")
    //@PreAuthorize("hasRole('ADMIN')")
    public String sayHello() {
        return "hello_world";
    }
}