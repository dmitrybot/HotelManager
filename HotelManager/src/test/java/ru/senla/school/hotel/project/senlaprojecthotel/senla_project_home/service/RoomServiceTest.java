package ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.constants.ProductChapter;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.constants.ProductSortType;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.constants.RoomSortType;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.constants.Status;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.entity.GuestEntity;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.entity.RoomEntity;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.entity.ServiceInfoEntity;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.exeption.CantChangeRoomStatusExeption;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.exeption.DBExeption;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.repository.IRoomRepository;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = TestConfig.class)
class RoomServiceTest extends AbstractUtilTest{

    @Autowired
    IRoomRepository roomRepository;

    @Autowired
    IRoomService roomService;

    @Test
    void changePriceForRoom() throws DBExeption {
        when(roomRepository.findByNumber(anyInt())).thenReturn(new RoomEntity());
        roomService.changePriceForRoom(1, 1);
        verify(roomRepository).save(any());
        when(roomRepository.findByNumber(anyInt())).thenReturn(null);
        Assertions.assertThrows(DBExeption.class, () -> {
            roomService.changePriceForRoom(1, 1);
        }, "Cant find data in db");
    }

    @Test
    void sortRooms() throws DBExeption {
        when(roomRepository.findAll()).thenReturn(Stream
                .of(new RoomEntity(100, ProductChapter.COMMON)
                        , new RoomEntity(200, ProductChapter.LUX)
                        , new RoomEntity(50, ProductChapter.COMMON))
                .collect(Collectors.toList()));

        assertEquals(new Integer(50), roomService.sortRooms(RoomSortType.PRICE).get(0).getPrice());
        assertEquals(new Integer(100), roomService.sortRooms(RoomSortType.PRICE).get(1).getPrice());
        assertEquals(new Integer(200), roomService.sortRooms(RoomSortType.PRICE).get(2).getPrice());
    }

    @Test
    void sortEmptyRooms() throws DBExeption {
        when(roomRepository.findAll()).thenReturn(Stream
                .of(new RoomEntity(100, ProductChapter.COMMON, Status.EMPTY)
                        , new RoomEntity(200, ProductChapter.LUX, Status.EMPTY)
                        , new RoomEntity(50, ProductChapter.COMMON, Status.SERVICED))
                .collect(Collectors.toList()));

        assertEquals(2, roomService.sortEmptyRooms(RoomSortType.PRICE).size());
        assertEquals(new Integer(100), roomService.sortEmptyRooms(RoomSortType.PRICE).get(0).getPrice());
        assertEquals(new Integer(200), roomService.sortEmptyRooms(RoomSortType.PRICE).get(1).getPrice());
    }

    @Test
    void getEmptyRoomCount() throws DBExeption {
        when(roomRepository.findAll()).thenReturn(Stream
                .of(new RoomEntity(100, ProductChapter.COMMON, Status.EMPTY)
                        , new RoomEntity(200, ProductChapter.LUX, Status.EMPTY)
                        , new RoomEntity(50, ProductChapter.COMMON, Status.SERVICED))
                .collect(Collectors.toList()));

        assertEquals(2, roomService.getEmptyRoomCount());
    }

    @Test
    void getThreeLastGuest() throws DBExeption {
        when(roomRepository.findByNumber(anyInt()))
                .thenReturn(new RoomEntity(Stream.of(new GuestEntity("01.01", "01.10")
                        , new GuestEntity("01.11", "01.12")
                        ,new GuestEntity("01.11", "01.12")
                ).collect(Collectors.toList())));

        assertEquals(3, roomService.getThreeLastGuest(2).size());

        when(roomRepository.findByNumber(anyInt()))
                .thenReturn(new RoomEntity(Stream.of(new GuestEntity("01.01", "01.10"))
                        .collect(Collectors.toList())));

        assertEquals(1, roomService.getThreeLastGuest(2).size());

        when(roomRepository.findByNumber(anyInt()))
                .thenReturn(new RoomEntity(Stream.of(new GuestEntity("01.01", "01.10")
                        , new GuestEntity("01.11", "01.12"))
                        .collect(Collectors.toList())));

        assertEquals(2, roomService.getThreeLastGuest(2).size());
    }
}