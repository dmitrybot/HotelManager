package ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.util;

import org.springframework.stereotype.Service;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.dto.GuestPutIntoDTO;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.dto.GuestServiceAddingDTO;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.dto.GuestWithoutServicesDTO;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.entity.GuestEntity;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.entity.ServiceEntity;

@Service
public class MappingUtils implements IMappingUtils {
    //из entity в dto
    public GuestServiceAddingDTO mapToGuestServiceAddingDTO(ServiceEntity entity) {
        GuestServiceAddingDTO dto = new GuestServiceAddingDTO();
        dto.setId(entity.getId());
        dto.setDate(entity.getDate());
        dto.setServiceInfo(entity.getServiceInfo());
        return dto;
    }

    //из dto в entity
    public ServiceEntity mapToServiceEntity(GuestServiceAddingDTO dto) {
        ServiceEntity entity = new ServiceEntity();
        entity.setId(dto.getId());
        entity.setDate(dto.getDate());
        entity.setServiceInfo(dto.getServiceInfo());
        return entity;
    }

    //из entity в dto
    public GuestWithoutServicesDTO mapToGuestWithoutServicesDTO(GuestEntity entity) {
        GuestWithoutServicesDTO dto = new GuestWithoutServicesDTO();
        dto.setId(entity.getId());
        dto.setRoom(entity.getRoom());
        dto.setGuestInfo(entity.getGuestInfo());
        dto.setCheckInDate(entity.getCheckInDate());
        dto.setCheckOutDate(entity.getCheckOutDate());
        return dto;
    }

    //из dto в entity
    public GuestEntity mapToGuestEntity(GuestWithoutServicesDTO dto) {
        GuestEntity entity = new GuestEntity();
        entity.setId(dto.getId());
        entity.setRoom(dto.getRoom());
        entity.setGuestInfo(dto.getGuestInfo());
        entity.setCheckInDate(dto.getCheckInDate());
        entity.setCheckOutDate(dto.getCheckOutDate());
        return entity;
    }

    public GuestPutIntoDTO mapToGuestPutIntoDTO(GuestPutIntoDTO entity) {
        GuestPutIntoDTO dto = new GuestPutIntoDTO();
        dto.setId(entity.getId());
        dto.setGuestInfo(entity.getGuestInfo());
        dto.setCheckInDate(entity.getCheckInDate());
        dto.setCheckOutDate(entity.getCheckOutDate());
        return dto;
    }

    //из dto в entity
    public GuestEntity mapToGuestEntity(GuestPutIntoDTO dto) {
        GuestEntity entity = new GuestEntity();
        entity.setId(dto.getId());
        entity.setGuestInfo(dto.getGuestInfo());
        entity.setCheckInDate(dto.getCheckInDate());
        entity.setCheckOutDate(dto.getCheckOutDate());
        return entity;
    }
}