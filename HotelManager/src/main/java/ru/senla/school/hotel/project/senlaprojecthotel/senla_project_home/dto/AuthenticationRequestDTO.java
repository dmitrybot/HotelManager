package ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.dto;

import lombok.Data;

@Data
public class AuthenticationRequestDTO {
    private String email;
    private String password;
}