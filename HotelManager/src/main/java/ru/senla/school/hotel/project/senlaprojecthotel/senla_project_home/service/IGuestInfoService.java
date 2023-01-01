package ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.service;

import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.entity.GuestInfoEntity;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.exeption.DBExeption;

import java.util.List;

public interface IGuestInfoService {

    void addGuest(GuestInfoEntity guest) throws DBExeption;

    void addGuests(List<GuestInfoEntity> guestInfoEntities) throws DBExeption;

    List<GuestInfoEntity> getGuestList() throws DBExeption;
}
