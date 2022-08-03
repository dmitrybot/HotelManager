package ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.dto;

import lombok.*;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.entity.GuestInfoEntity;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class GuestPutIntoDTO {
    private Integer id;
    private GuestInfoEntity guestInfo;
    private String checkInDate;
    private String checkOutDate;
}
