package ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.constants.ProductChapter;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.constants.Status;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.entity.GuestEntity;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.entity.RoomEntity;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.exeption.DBExeption;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.repository.IGuestRepository;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = TestConfig.class)
class GuestServiceTest extends AbstractUtilTest {

    @Autowired
    IGuestService guestService;

    @Autowired
    IGuestRepository guestRepository;

    @Test
    void sortGuestsIn() throws DBExeption {
        when(guestRepository.findAll()).thenReturn(Stream
                .of(new GuestEntity(true, "01.01", "02.02")
                        , new GuestEntity(false, "01.03", "01.12")
                        , new GuestEntity(true, "01.02", "02.01"))
                .collect(Collectors.toList()));

        assertEquals(2, guestService.sortGuestsIn().size());

        assertEquals("02.01", guestService.sortGuestsIn().get(0).getCheckOutDate());
    }

    @Test
    void getGuestsCount() throws DBExeption {
        when(guestRepository.findAll()).thenReturn(Stream
                .of(new GuestEntity(true, "01.01", "02.02")
                        , new GuestEntity(false, "01.03", "01.12")
                        , new GuestEntity(true, "01.02", "02.01"))
                .collect(Collectors.toList()));

        assertEquals(2, guestService.getGuestsCount());
    }
}