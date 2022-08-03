package ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "service")
public class ServiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String date;
    @ManyToOne
    @JoinColumn(name = "guest_id")
    private GuestEntity guest;
    @ManyToOne
    @JoinColumn(name = "serviceinfo_id")
    private ServiceInfoEntity serviceInfo;
}
