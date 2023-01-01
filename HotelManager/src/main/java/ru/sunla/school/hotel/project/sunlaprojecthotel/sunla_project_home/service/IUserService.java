package ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.service;

import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.entity.UserEntity;

public interface IUserService {
    UserEntity findByEmail(String email);
}
