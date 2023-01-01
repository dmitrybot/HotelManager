package ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "guest")
public class GuestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "room_id")
    private RoomEntity room;
    @ManyToOne
    @JoinColumn(name = "guestinfo_id")
    private GuestInfoEntity guestInfo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "guest")
    private List<ServiceEntity> services = new ArrayList<>();
    private String checkInDate;
    private String checkOutDate;
    private boolean isIn;

    public GuestEntity(boolean isIn, String checkInDate, String checkOutDate) {
        this.isIn = isIn;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public GuestEntity(String checkInDate, String checkOutDate) {
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public void addService(ServiceEntity serviceEntity) {
        services.add(serviceEntity);
    }
}
