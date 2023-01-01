package ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.service;

import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.constants.RoomSortType;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.constants.Status;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.entity.GuestEntity;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.entity.RoomEntity;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.exeption.CantChangeRoomStatusExeption;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.exeption.DBExeption;

import java.util.List;

public interface IRoomService {
    List<RoomEntity> getRooms() throws DBExeption;

    RoomEntity putInRoom(GuestEntity guest, int number) throws DBExeption;

    void evictFromRoom(int number) throws DBExeption;

    void changeStatus(int number, Status status) throws CantChangeRoomStatusExeption, DBExeption;

    void changePriceForRoom(int number, int price) throws DBExeption;

    void addRoom(RoomEntity room) throws DBExeption;

    List<RoomEntity> sortRooms(RoomSortType roomSortType) throws DBExeption;

    List<RoomEntity> sortEmptyRooms(RoomSortType roomSortType) throws DBExeption;

    List<RoomEntity> getDateEmptyRooms(String date) throws DBExeption;

    int getEmptyRoomCount() throws DBExeption;

    RoomEntity findRoom(int number) throws DBExeption;

    List<GuestEntity> getThreeLastGuest(int number) throws DBExeption;
}
