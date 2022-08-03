package ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.repository;

import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.entity.RoomEntity;

import java.util.List;

public interface IRoomRepository {
   void save(RoomEntity room);
   void saveAll(List<RoomEntity> services);
   List<RoomEntity> findAll();
   int count();
   RoomEntity findByNumber(int number);
}
