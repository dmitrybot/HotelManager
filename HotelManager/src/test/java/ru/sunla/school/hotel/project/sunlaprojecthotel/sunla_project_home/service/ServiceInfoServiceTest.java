package ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.entity.RoomEntity;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.entity.ServiceInfoEntity;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.exeption.DBExeption;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.repository.IServiceInfoRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = TestConfig.class)
class ServiceInfoServiceTest extends AbstractUtilTest {

    @Autowired
    IServiceInfoService serviceInfoService;

    @Autowired
    IServiceInfoRepository serviceInfoRepository;

    @Test
    void changePriceForService() throws DBExeption {
        when(serviceInfoRepository.findById(anyInt())).thenReturn(new ServiceInfoEntity());
        serviceInfoService.changePriceForService(1, 1);
        verify(serviceInfoRepository).save(any());
        when(serviceInfoRepository.findById(anyInt())).thenReturn(null);
        Assertions.assertThrows(DBExeption.class, () -> {
            serviceInfoService.changePriceForService(1, 1);
        }, "Cant find data in db");
    }
}