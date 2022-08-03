package ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.service;

import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.constants.ProductSortType;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.entity.ProductEntity;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.exeption.DBExeption;

import java.util.List;

public interface IHotelService {
    List<ProductEntity> getSortedProducts(ProductSortType productSortType) throws DBExeption;
}
