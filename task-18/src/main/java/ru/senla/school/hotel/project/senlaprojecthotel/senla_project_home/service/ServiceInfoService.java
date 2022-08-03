package ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.service;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.entity.ServiceInfoEntity;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.exeption.DBExeption;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.repository.IServiceInfoRepository;

import javax.persistence.NoResultException;
import java.util.List;

@Service
public class ServiceInfoService implements IServiceInfoService {

    private IServiceInfoRepository serviceInfoRepository;

    private static final Logger log = Logger.getLogger(ServiceInfoEntity.class);

    public ServiceInfoService(IServiceInfoRepository serviceInfoRepository) {
        this.serviceInfoRepository = serviceInfoRepository;
    }

    @Transactional(timeout = 10)
    public List<ServiceInfoEntity> getServices() throws DBExeption {
        try {
            List<ServiceInfoEntity> serviceInfoEntities = serviceInfoRepository.findAll();
            return serviceInfoEntities;
        } catch (HibernateException | NoResultException | NullPointerException e) {
            log.error("db servicesifno get operation failed");
            throw new DBExeption();
        }
    }

    @Transactional(timeout = 10)
    public void changePriceForService(int id, int price) throws DBExeption {
        try {
            ServiceInfoEntity serviceInfoEntity = serviceInfoRepository.findById(id);
            serviceInfoEntity.setPrice(price);
            serviceInfoRepository.save(serviceInfoEntity);
        } catch (HibernateException | NoResultException | NullPointerException e) {
            log.error("db serviceifno price change operation failed");
            throw new DBExeption();
        }
    }

    @Transactional(timeout = 10)
    public void addServices(List<ServiceInfoEntity> serviceInfoEntities) throws DBExeption {
        try {
            serviceInfoRepository.saveAll(serviceInfoEntities);
        } catch (HibernateException | NoResultException | NullPointerException e) {
            log.error("db servicesifno add operation failed");
            throw new DBExeption();
        }
    }

    @Transactional(timeout = 10)
    public void addService(ServiceInfoEntity serviceInfo) throws DBExeption {
        try {
            serviceInfoRepository.save(serviceInfo);
        } catch (HibernateException | NoResultException | NullPointerException e) {
            log.error("db serviceifno add operation failed");
            throw new DBExeption();
        }
    }
}
