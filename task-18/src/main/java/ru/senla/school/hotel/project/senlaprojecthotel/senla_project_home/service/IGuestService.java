package ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.service;


import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.constants.ServiceSortType;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.entity.GuestEntity;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.entity.ServiceEntity;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.exeption.DBExeption;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.exeption.FindByIdException;

import java.util.List;

public interface IGuestService {
    void addGuest(GuestEntity guest) throws DBExeption;

    void addGuests(List<GuestEntity> guestEntities) throws DBExeption;

    List<GuestEntity> getGuestList() throws DBExeption;

    List<GuestEntity> sortGuestsIn() throws DBExeption;

    int getGuestsCount() throws DBExeption;

    int getGuestCheck(int id) throws FindByIdException, DBExeption;

    List<ServiceEntity> getSortedGuestsServices(int id, ServiceSortType serviceSortType) throws FindByIdException, DBExeption;

    void addServiceToGuest(ServiceEntity service, int id) throws FindByIdException, DBExeption;
}
