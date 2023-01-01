package ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.repository;

import org.hibernate.Session;
import org.springframework.stereotype.Component;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.entity.ServiceInfoEntity;

import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Component
public class ServiceInfoRepository implements IServiceInfoRepository {

    @PersistenceContext
    private Session session;

    public void save(ServiceInfoEntity service) {
        session.saveOrUpdate(service);
    }

    public void saveAll(List<ServiceInfoEntity> services) {
        services.forEach(s -> {
            session.saveOrUpdate(s);
        });
    }

    public List<ServiceInfoEntity> findAll() {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ServiceInfoEntity> criteria = builder.createQuery(ServiceInfoEntity.class);
        criteria.from(ServiceInfoEntity.class);
        return session.createQuery(criteria)
                .getResultList();
    }

    public int count() {
        return (Integer) session.createQuery("SELECT COUNT(s) from ServiceInfoEntity s")
                .getSingleResult();
    }

    public ServiceInfoEntity findById(int id) {
        return session.get(ServiceInfoEntity.class, id);
    }
}
