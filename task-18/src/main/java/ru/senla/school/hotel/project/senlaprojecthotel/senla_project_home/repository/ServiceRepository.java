package ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.repository;

import org.hibernate.Session;
import org.springframework.stereotype.Component;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.entity.ServiceEntity;

import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Component
public class ServiceRepository implements IServiceRepository {

    @PersistenceContext
    private Session session;

    public void save(ServiceEntity service) {
        session.saveOrUpdate(service);
    }

    public void saveAll(List<ServiceEntity> services) {
        services.forEach(s -> {
            session.saveOrUpdate(s);
        });
    }

    public List<ServiceEntity> findAll() {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ServiceEntity> criteria = builder.createQuery(ServiceEntity.class);
        criteria.from(ServiceEntity.class);
        return session.createQuery(criteria)
                .getResultList();
    }

    public int count() {
        return (Integer) session.createQuery("SELECT COUNT(s) from ServiceEntity s")
                .getSingleResult();
    }

    public ServiceEntity findById(int id) {
        return session.get(ServiceEntity.class, id);
    }
}
