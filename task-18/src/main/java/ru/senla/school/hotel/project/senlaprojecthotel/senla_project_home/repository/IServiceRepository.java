package ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.repository;

import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.entity.ServiceEntity;

import java.util.List;

public interface IServiceRepository {
    void save(ServiceEntity service);
    void saveAll(List<ServiceEntity> services);
    List<ServiceEntity> findAll();
    int count();
    ServiceEntity findById(int id);
}
