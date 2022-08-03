package ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan("ru.senla.school.hotel.project.senlaprojecthotel")
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

}
