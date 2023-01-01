package ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.repository;

import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.entity.UserEntity;

public interface IUserRepository {
    UserEntity findByEmail(String email);
}
