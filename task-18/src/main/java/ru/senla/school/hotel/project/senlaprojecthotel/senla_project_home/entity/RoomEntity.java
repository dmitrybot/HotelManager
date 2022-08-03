package ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.constants.ProductChapter;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.constants.Status;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "room")
public class RoomEntity extends ProductEntity {
    @Id
    private Integer number;
    private Status status = Status.EMPTY;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "room")
    private List<GuestEntity> guests = new ArrayList<>();
    private Integer capacity;
    private Integer starNumber;

    public RoomEntity(List<GuestEntity> guests) {
        this.guests = guests;
    }

    public RoomEntity(int price, ProductChapter chapter) {
        super(price, chapter);
    }

    public RoomEntity(int price, ProductChapter chapter, Status status) {
        super(price, chapter);
        this.status = status;
    }

    public RoomEntity(int price, int number, ProductChapter chapter, int capacity, int starNumber) {
        super(price, chapter);
        this.number = number;
        this.capacity = capacity;
        this.starNumber = starNumber;
    }

    public RoomEntity(int price, int number, ProductChapter chapter, Status status, int capacity, int starNumber) {
        super(price, chapter);
        this.status = status;
        this.number = number;
        this.capacity = capacity;
        this.starNumber = starNumber;
    }

    public RoomEntity(int price, ProductChapter chapter, Integer number, Status status, List<GuestEntity> guests, Integer capacity, Integer starNumber) {
        super(price, chapter);
        this.number = number;
        this.status = status;
        this.guests = guests;
        this.guests.forEach(s -> {
            s.setRoom(this);
        });
        this.capacity = capacity;
        this.starNumber = starNumber;
    }

    public void addGuest(GuestEntity guest, Integer count) {
        if (guests.stream().noneMatch(s -> s.getId() == guest.getId()))
            if (guests.size() == count) {
                guests.remove(0);
            }
        guests.add(guest);
    }

    public void deleteGuest() {
        guests.remove(guests.size() - 1);
        this.status = Status.EMPTY;
    }

    public List<GuestEntity> findThreeLastGuest() {
        List<GuestEntity> guestEntities = new ArrayList<>();
        int s = guests.size();
        int i = 0;
        int k = 1;
        if (status == Status.SERVICED) k = 2;
        while (s - i - k >= 0 && i != 3) {
            guestEntities.add(guests.get(s - i - k));
            i++;
        }
        return guestEntities;
    }

    public String findLastDate() {
        if (guests.size() == 0) return "a";
        return guests.get(guests.size() - 1).getCheckOutDate();
    }

    public GuestEntity findGuestIn() {
        if (status == Status.SERVICED) {
            return guests.get(guests.size() - 1);
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        return "RoomEntity{" +
                "number=" + number +
                ", status=" + status +
                ", guests=" + guests +
                ", capacity=" + capacity +
                ", starNumber=" + starNumber +
                '}';
    }
}
