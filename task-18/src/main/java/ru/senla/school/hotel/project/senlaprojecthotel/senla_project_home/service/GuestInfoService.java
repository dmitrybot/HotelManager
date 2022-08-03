package ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.service;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.entity.GuestInfoEntity;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.exeption.DBExeption;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.repository.IGuestInfoRepository;

import javax.persistence.NoResultException;
import java.util.List;

@Service
public class GuestInfoService {

    private IGuestInfoRepository guestInfoRepository;

    private static final Logger log = Logger.getLogger(GuestInfoService.class);

    public GuestInfoService(IGuestInfoRepository guestInfoRepository) {
        this.guestInfoRepository = guestInfoRepository;
    }

    @Transactional(timeout = 10)
    public void addGuest(GuestInfoEntity guest) throws DBExeption {
        try {
            guestInfoRepository.save(guest);
        } catch (HibernateException | NoResultException | NullPointerException e) {
            log.error("db guestinfo add operation failed");
            throw new DBExeption();
        }
    }

    @Transactional(timeout = 10)
    public void addGuests(List<GuestInfoEntity> guestInfoEntities) throws DBExeption {
        try {
            guestInfoRepository.saveAll(guestInfoEntities);
        } catch (HibernateException | NoResultException | NullPointerException e) {
            log.error("db guestsinfo add operation failed");
            throw new DBExeption();
        }
    }

    @Transactional(timeout = 10)
    public List<GuestInfoEntity> getGuestList() throws DBExeption {
        try {
            List<GuestInfoEntity> guestInfoEntities = guestInfoRepository.findAll();
            return guestInfoEntities;
        } catch (HibernateException | NoResultException | NullPointerException e) {
            log.error("db guestsinfo get operation failed");
            throw new DBExeption();
        }
    }
}
