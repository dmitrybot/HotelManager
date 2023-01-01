package ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.repository;

import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.entity.ServiceInfoEntity;

import java.util.List;

public interface IServiceInfoRepository {
    void save(ServiceInfoEntity service);
    void saveAll(List<ServiceInfoEntity> services);
    List<ServiceInfoEntity> findAll();
    int count();
    ServiceInfoEntity findById(int id);
}
