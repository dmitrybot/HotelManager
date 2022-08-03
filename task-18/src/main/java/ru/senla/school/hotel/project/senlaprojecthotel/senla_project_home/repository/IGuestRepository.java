package ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.repository;


import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.entity.GuestEntity;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.exeption.FindByIdException;

import java.util.List;

public interface IGuestRepository {
    void save(GuestEntity guest);
    List<GuestEntity> findAll();
    int count();
    GuestEntity findById(int id) throws FindByIdException;
    void saveAll(List<GuestEntity> services);
}
