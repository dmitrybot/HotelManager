package ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.entity;

import lombok.*;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.constants.ProductChapter;

import javax.persistence.*;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "serviceinfo")
public class ServiceInfoEntity extends ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "serviceInfo")
    private List<ServiceEntity> services;
    private String serviceName;
    private String category;

    public ServiceInfoEntity(Integer price, ProductChapter chapter) {
        super(price, chapter);
    }
}
