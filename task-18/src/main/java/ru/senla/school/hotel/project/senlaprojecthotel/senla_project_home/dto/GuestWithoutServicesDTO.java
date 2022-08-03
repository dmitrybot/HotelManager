package ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.dto;

import lombok.*;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.entity.GuestInfoEntity;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.entity.RoomEntity;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class GuestWithoutServicesDTO {
    private Integer id;
    private RoomEntity room;
    private GuestInfoEntity guestInfo;
    private String checkInDate;
    private String checkOutDate;
}
