package ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.constants.ProductChapter;

import javax.persistence.MappedSuperclass;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@MappedSuperclass
public abstract class ProductEntity {
    private Integer price;
    private ProductChapter chapter;
}
