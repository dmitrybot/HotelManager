package ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.constants.ProductSortType;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.entity.ProductEntity;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.exeption.DBExeption;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.service.IHotelService;

import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    IHotelService hotelService;

    public HotelController(IHotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping(value = "/product", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductEntity> getSortedProducts(@RequestParam(name = "sort", required = false)
                                                         ProductSortType productSortType) throws DBExeption {
        return hotelService.getSortedProducts(productSortType);
    }
}
