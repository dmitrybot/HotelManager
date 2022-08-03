package ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "guestinfo")
public class GuestInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "guestInfo")
    private List<GuestEntity> guests;
    private String birthData;
    private String email;
    private String firstname;
    private String secondname;
    private String lastname;
    private String phone;
    private String sex;
}
