package ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.repository;

import org.hibernate.Session;
import org.springframework.stereotype.Component;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.entity.GuestEntity;

import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Component
public class GuestRepository implements IGuestRepository {

    @PersistenceContext
    private Session session;

    public void save(GuestEntity guest) {
        session.saveOrUpdate(guest);
    }

    public void saveAll(List<GuestEntity> guests) {
        guests.forEach(s -> {
            session.saveOrUpdate(s);
        });
    }

    public List<GuestEntity> findAll() {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<GuestEntity> criteria = builder.createQuery(GuestEntity.class);
        criteria.from(GuestEntity.class);
        return session.createQuery(criteria).getResultList();
    }

    public GuestEntity findById(int id) {
        return session.get(GuestEntity.class, id);
    }

    public int count() {
        return (Integer) session.createQuery("SELECT COUNT(s) from GuestEntity s").getSingleResult();
    }
}
