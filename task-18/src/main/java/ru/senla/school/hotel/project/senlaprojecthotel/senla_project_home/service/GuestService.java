package ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.service;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.constants.ServiceSortType;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.entity.GuestEntity;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.entity.ServiceEntity;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.exeption.DBExeption;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.exeption.FindByIdException;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.repository.IGuestRepository;

import javax.persistence.NoResultException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GuestService implements IGuestService {

    private IGuestRepository guestRepository;
    @Lazy
    private IRoomService roomService;
    @Lazy
    private IServiceService serviceService;

    private static final Logger log = Logger.getLogger(GuestService.class);

    public GuestService(IGuestRepository guestRepository, IRoomService roomService, IServiceService serviceService) {
        this.guestRepository = guestRepository;
        this.roomService = roomService;
        this.serviceService = serviceService;
    }

    @Transactional(timeout = 10)
    public void addGuest(GuestEntity guest) throws DBExeption {
        try {
            guestRepository.save(guest);
        } catch (HibernateException | NoResultException | NullPointerException e) {
            log.error("db guest add operation failed");
            throw new DBExeption();
        }
    }

    @Transactional(timeout = 10)
    public void addGuests(List<GuestEntity> guestEntities) throws DBExeption {
        try {
            guestRepository.saveAll(guestEntities);
            guestEntities.forEach(g -> {
                try {
                    serviceService.addServices(g.getServices());
                } catch (DBExeption dbExeption) {
                    dbExeption.printStackTrace();
                }
            });
        } catch (HibernateException | NoResultException | NullPointerException e) {
            log.error("db guests add operation failed");
            throw new DBExeption();
        }
    }

    @Transactional(timeout = 10)
    public List<GuestEntity> getGuestList() throws DBExeption {
        try {
            List<GuestEntity> guestEntities = guestRepository.findAll();
            return guestEntities;
        } catch (HibernateException | NoResultException | NullPointerException e) {
            log.error("db guests get operation failed");
            throw new DBExeption();
        }
    }

    @Transactional(timeout = 10)
    public List<GuestEntity> sortGuestsIn() throws DBExeption {
        Comparator<GuestEntity> comparator = Comparator.comparing(obj -> obj.getCheckOutDate());
        return getGuestList().stream()
                .filter(o -> o.isIn())
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    @Transactional(timeout = 10)
    public int getGuestsCount() throws DBExeption {
        try {
            Integer i = getGuestList()
                    .stream()
                    .filter(o -> o.isIn())
                    .collect(Collectors.toList())
                    .size();
            return i;
        } catch (HibernateException | NoResultException | NullPointerException e) {
            log.error("db guests count get operation failed");
            throw new DBExeption();
        }
    }

    @Transactional(timeout = 10)
    public int getGuestCheck(int id) throws FindByIdException, DBExeption {
        try {
            if (guestRepository.findById(id) == null) throw new FindByIdException();

            GuestEntity guest = guestRepository.findById(id);
            return roomService.findRoom(guest.getRoom().getNumber()).getPrice()
                    + guest.getServices()
                    .stream()
                    .mapToInt(i -> i.getServiceInfo().getPrice())
                    .sum();
        } catch (HibernateException | NoResultException | NullPointerException e) {
            log.error("db guests check get operation failed");
            throw new DBExeption();
        }
    }

    @Transactional(timeout = 10)
    public List<ServiceEntity> getSortedGuestsServices(int id, ServiceSortType serviceSortType) throws FindByIdException, DBExeption {
        Comparator<ServiceEntity> comparator = Comparator.comparing(obj -> obj.getId());
        switch (serviceSortType) {
            case PRICE:
                comparator = Comparator.comparing(obj -> obj.getServiceInfo().getPrice());
                break;
            case DATE:
                comparator = Comparator.comparing(obj -> obj.getDate());
                break;
        }
        try {
            List<ServiceEntity> serviceEntities = guestRepository.findById(id).getServices().stream()
                    .sorted(comparator)
                    .collect(Collectors.toList());
            return serviceEntities;
        } catch (HibernateException | NoResultException | NullPointerException e) {
            log.error("db guests sorted services get operation failed");
            throw new DBExeption();
        }
    }

    @Transactional(timeout = 10)
    public void addServiceToGuest(ServiceEntity service, int id) throws FindByIdException, DBExeption {
        try {
            GuestEntity guestEntity = guestRepository.findById(id);
            guestEntity.addService(service);
        } catch (HibernateException | NoResultException | NullPointerException e) {
            log.error("db guests service add operation failed");
            throw new DBExeption();
        }
    }
}
