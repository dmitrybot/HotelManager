package ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.constants.ProductChapter;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.constants.ProductSortType;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.entity.ProductEntity;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.entity.RoomEntity;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.entity.ServiceInfoEntity;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.exeption.DBExeption;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.repository.IRoomRepository;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.repository.IServiceInfoRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = TestConfig.class)
public class HotelServiceTest extends AbstractUtilTest{

    @Autowired
    IHotelService hotelService;

    @Autowired
    IRoomRepository roomRepository;

    @Autowired
    IServiceInfoRepository serviceRepository;

    @Test
    public void getSortedProducts() throws DBExeption {
        when(roomRepository.findAll()).thenReturn(Stream
                .of(new RoomEntity(100, ProductChapter.COMMON)
                , new RoomEntity(200, ProductChapter.LUX)
                , new RoomEntity(50, ProductChapter.COMMON))
                .collect(Collectors.toList()));
        when(serviceRepository.findAll()).thenReturn(Stream
                .of(new ServiceInfoEntity(200, ProductChapter.COMMON)
                        , new ServiceInfoEntity(130, ProductChapter.LUX)
                        , new ServiceInfoEntity(500, ProductChapter.COMMON))
                .collect(Collectors.toList()));

        assertEquals(new Integer(50), hotelService.getSortedProducts(ProductSortType.PRICE).get(0).getPrice());
        assertEquals(new Integer(100), hotelService.getSortedProducts(ProductSortType.PRICE).get(1).getPrice());
        assertEquals(new Integer(130), hotelService.getSortedProducts(ProductSortType.PRICE).get(2).getPrice());
        assertEquals(new Integer(200), hotelService.getSortedProducts(ProductSortType.PRICE).get(3).getPrice());
        assertEquals(new Integer(200), hotelService.getSortedProducts(ProductSortType.PRICE).get(4).getPrice());
        assertEquals(new Integer(500), hotelService.getSortedProducts(ProductSortType.PRICE).get(5).getPrice());
    }
}