package ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.repository;

import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.entity.GuestInfoEntity;

import java.util.List;

public interface IGuestInfoRepository {
    void save(GuestInfoEntity guest);
    void saveAll(List<GuestInfoEntity> guests);
    List<GuestInfoEntity> findAll();
    GuestInfoEntity findById(int id);
}
