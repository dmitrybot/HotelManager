package ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.util;

import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.dto.GuestPutIntoDTO;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.dto.GuestServiceAddingDTO;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.dto.GuestWithoutServicesDTO;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.entity.GuestEntity;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.entity.ServiceEntity;

public interface IMappingUtils {
    GuestServiceAddingDTO mapToGuestServiceAddingDTO(ServiceEntity entity);
    ServiceEntity mapToServiceEntity(GuestServiceAddingDTO dto);
    GuestWithoutServicesDTO mapToGuestWithoutServicesDTO(GuestEntity entity);
    GuestEntity mapToGuestEntity(GuestWithoutServicesDTO dto);
    GuestPutIntoDTO mapToGuestPutIntoDTO(GuestPutIntoDTO entity);
    GuestEntity mapToGuestEntity(GuestPutIntoDTO dto);
}
