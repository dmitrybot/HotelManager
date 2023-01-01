package ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.service;

import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.entity.ServiceInfoEntity;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.exeption.DBExeption;

import java.util.List;

public interface IServiceInfoService {
    List<ServiceInfoEntity> getServices() throws DBExeption;
    void changePriceForService(int id, int price) throws DBExeption;
    void addServices(List<ServiceInfoEntity> serviceInfoEntities) throws DBExeption;
    void addService(ServiceInfoEntity serviceInfo) throws DBExeption;
}
