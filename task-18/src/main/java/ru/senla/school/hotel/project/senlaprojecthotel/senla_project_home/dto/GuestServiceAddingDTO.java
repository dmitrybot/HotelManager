package ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.dto;

import lombok.*;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.entity.ServiceInfoEntity;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class GuestServiceAddingDTO {
    private Integer id;
    private String date;
    private ServiceInfoEntity serviceInfo;
}
