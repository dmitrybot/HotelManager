package ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.service;

import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.entity.ServiceEntity;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.exeption.DBExeption;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.exeption.FindByIdException;

import java.util.List;

public interface IServiceService {
    List<ServiceEntity> getServices() throws DBExeption;
    void addServices(List<ServiceEntity> serviceEntities) throws DBExeption;
    void addService(ServiceEntity service, int id) throws DBExeption, FindByIdException;
}
