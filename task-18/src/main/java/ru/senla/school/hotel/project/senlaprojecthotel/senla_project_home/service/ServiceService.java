package ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.service;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.entity.ServiceEntity;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.exeption.DBExeption;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.exeption.FindByIdException;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.repository.IGuestRepository;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.repository.IServiceRepository;

import javax.persistence.NoResultException;
import java.util.List;

@Service
public class ServiceService implements IServiceService {

    private IServiceRepository serviceRepository;
    private IGuestRepository guestRepository;

    private static final Logger log = Logger.getLogger(ServiceEntity.class);

    public ServiceService(IServiceRepository serviceRepository, IGuestRepository guestRepository) {
        this.serviceRepository = serviceRepository;
        this.guestRepository = guestRepository;
    }

    @Transactional(timeout = 10)
    public List<ServiceEntity> getServices() throws DBExeption {
        try {
            List<ServiceEntity> s = serviceRepository.findAll();
            return s;
        } catch (HibernateException | NoResultException | NullPointerException e) {
            log.error("db services get operation failed");
            throw new DBExeption();
        }
    }

    @Transactional(timeout = 10)
    public void addServices(List<ServiceEntity> serviceEntities) throws DBExeption {
        try {
            serviceRepository.saveAll(serviceEntities);
        } catch (HibernateException | NoResultException | NullPointerException e) {
            log.error("db services add operation failed");
            throw new DBExeption();
        }
    }

    @Transactional(timeout = 10)
    public void addService(ServiceEntity service, int id) throws DBExeption, FindByIdException {
        try {
            service.setGuest(guestRepository.findById(id));
            serviceRepository.save(service);
        } catch (HibernateException | NoResultException | NullPointerException e) {
            log.error("db service add operation failed");
            throw new DBExeption();
        }
    }
}
