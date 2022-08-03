package ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.service;


import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.constants.RoomSortType;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.constants.Status;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.entity.GuestEntity;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.entity.RoomEntity;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.exeption.CantChangeRoomStatusExeption;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.exeption.DBExeption;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.repository.IRoomRepository;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomService implements IRoomService {

    private IRoomRepository roomRepository;
    private IGuestService guestService;

    @Value("#{new Integer('${proj.service.RoomService.lastGuestsSave}')}")
    private Integer lastGuestsSave;
    @Value("#{new Boolean('${proj.service.RoomService.roomStatusChange}')}")
    private Boolean roomStatusChange = true;

    private static final Logger log = Logger.getLogger(RoomService.class);

    public RoomService(IRoomRepository roomRepository, @Lazy IGuestService guestService) {
        this.roomRepository = roomRepository;
        this.guestService = guestService;
    }

    @Transactional(timeout = 10)
    public List<RoomEntity> getRooms() throws DBExeption {
        try {
            List<RoomEntity> roomEntities = roomRepository.findAll();
            return roomEntities;
        } catch (HibernateException | NoResultException | NullPointerException e) {
            log.error("db rooms get operation failed");
            throw new DBExeption();
        }
    }

    @Transactional(timeout = 10)
    public RoomEntity putInRoom(GuestEntity guest, int number) throws DBExeption {
        try {
            RoomEntity room = roomRepository.findByNumber(number);
            room.addGuest(guest, lastGuestsSave);
            roomRepository.save(room);
            return room;
        } catch (HibernateException | NoResultException | NullPointerException e) {
            log.error("db room guest add operation failed");
            throw new DBExeption();
        }
    }

    @Transactional(timeout = 10)
    public void evictFromRoom(int number) throws DBExeption {
        try {
            RoomEntity room = roomRepository.findByNumber(number);
            room.setStatus(Status.EMPTY);
            room.findGuestIn().setIn(false);
            roomRepository.save(room);
        } catch (HibernateException | NoResultException | NullPointerException e) {
            log.error("db room evict operation failed");
            throw new DBExeption();
        }
    }

    @Transactional(timeout = 10)
    public void changeStatus(int number, Status status) throws CantChangeRoomStatusExeption, DBExeption {
        try {
            if (roomStatusChange) {
                RoomEntity room = roomRepository.findByNumber(number);
                room.setStatus(status);
                roomRepository.save(room);
            } else {
                throw new CantChangeRoomStatusExeption();
            }
        } catch (HibernateException | NoResultException | NullPointerException e) {
            log.error("db room change status operation failed");
            throw new DBExeption();
        }
    }

    @Transactional(timeout = 10)
    public void changePriceForRoom(int number, int price) throws DBExeption {
        try {
            RoomEntity room = roomRepository.findByNumber(number);
            room.setPrice(price);
            roomRepository.save(room);
        } catch (HibernateException | NoResultException | NullPointerException e) {
            log.error("db room change price operation failed");
            throw new DBExeption();
        }
    }

    @Transactional(timeout = 10)
    public void addRoom(RoomEntity room) throws DBExeption {
        try {
            roomRepository.save(room);
        } catch (HibernateException | NoResultException | NullPointerException e) {
            e.printStackTrace();
            log.error("db room add operation failed");
            throw new DBExeption();
        }
    }

    @Transactional()
    public List<RoomEntity> sortRooms(RoomSortType roomSortType) throws DBExeption {
        Comparator<RoomEntity> comparator = Comparator.comparing(obj -> obj.getNumber());
        switch (roomSortType) {
            case PRICE:
                comparator = Comparator.comparing(obj -> obj.getPrice());
                break;
            case CAPACITY:
                comparator = Comparator.comparing(obj -> obj.getCapacity());
                break;
            case STAR_NUMBER:
                comparator = Comparator.comparing(obj -> obj.getStarNumber());
                break;
        }
        try {
            List<RoomEntity> roomEntities = roomRepository.findAll().stream()
                    .sorted(comparator)
                    .collect(Collectors.toList());
            return roomEntities;
        } catch (HibernateException | NoResultException | NullPointerException e) {
            e.printStackTrace();
            log.error("db rooms sorted get operation failed");
            throw new DBExeption();
        }
    }

    @Transactional(timeout = 10)
    public List<RoomEntity> sortEmptyRooms(RoomSortType roomSortType) throws DBExeption {
        Comparator<RoomEntity> comparator = Comparator.comparing(obj -> obj.getNumber());
        switch (roomSortType) {
            case PRICE:
                comparator = Comparator.comparing(obj -> obj.getPrice());
                break;
            case CAPACITY:
                comparator = Comparator.comparing(obj -> obj.getCapacity());
                break;
            case STAR_NUMBER:
                comparator = Comparator.comparing(obj -> obj.getStarNumber());
                break;
        }
        try {
            List<RoomEntity> roomEntities = this.getEmptyRooms().stream().sorted(comparator).collect(Collectors.toList());
            return roomEntities;
        } catch (HibernateException | NoResultException | NullPointerException e) {
            log.error("db empty room add operation failed");
            throw new DBExeption();
        }
    }

    @Transactional(timeout = 10)
    public List<RoomEntity> getDateEmptyRooms(String date) throws DBExeption {
        try {
            List<RoomEntity> emRooms = new ArrayList<>();
            for (RoomEntity room : roomRepository.findAll()) {
                if (room.getStatus() == Status.EMPTY || room.findLastDate().compareTo(date) < 0) {
                    emRooms.add(room);
                }
            }
            return emRooms;
        } catch (HibernateException | NoResultException | NullPointerException e) {
            log.error("db empty room date get operation failed");
            throw new DBExeption();
        }
    }

    @Transactional(timeout = 10)
    public int getEmptyRoomCount() throws DBExeption {
        try {
            Integer i = getEmptyRooms().size();
            return i;
        } catch (HibernateException | NoResultException | NullPointerException e) {
            log.error("db empty room count get operation failed");
            throw new DBExeption();
        }
    }

    @Transactional(timeout = 10)
    public RoomEntity findRoom(int number) throws DBExeption {
        try {
            RoomEntity i = roomRepository.findByNumber(number);
            return i;
        } catch (HibernateException | NoResultException | NullPointerException e) {
            log.error("db room get operation failed");
            throw new DBExeption();
        }
    }

    @Transactional(timeout = 10)
    public List<RoomEntity> getEmptyRooms() throws DBExeption {
        try {
            List<RoomEntity> i = roomRepository.findAll().stream()
                    .filter(p -> p.getStatus().equals(Status.EMPTY))
                    .collect(Collectors.toList());
            return i;
        } catch (HibernateException | NoResultException | NullPointerException e) {
            log.error("db empty rooms get operation failed");
            throw new DBExeption();
        }
    }

    @Transactional(timeout = 10)
    public List<GuestEntity> getThreeLastGuest(int number) throws DBExeption {
        try {
            return roomRepository.findByNumber(number).findThreeLastGuest();
        } catch (HibernateException | NoResultException | NullPointerException e) {
            log.error("db empty rooms get operation failed");
            throw new DBExeption();
        }
    }
}
