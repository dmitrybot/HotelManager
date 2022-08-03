package ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.repository;

import org.hibernate.Session;
import org.springframework.stereotype.Component;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.entity.GuestInfoEntity;

import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Component
public class GuestInfoRepository implements IGuestInfoRepository {

    @PersistenceContext
    private Session session;

    public void save(GuestInfoEntity guest) {
        session.saveOrUpdate(guest);
    }

    public void saveAll(List<GuestInfoEntity> guests) {
        guests.forEach(s -> {
            session.saveOrUpdate(s);
        });
    }

    public List<GuestInfoEntity> findAll() {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<GuestInfoEntity> criteria = builder.createQuery(GuestInfoEntity.class);
        criteria.from(GuestInfoEntity.class);
        return session.createQuery(criteria).getResultList();
    }

    public GuestInfoEntity findById(int id) {
        return session.get(GuestInfoEntity.class, id);
    }
}
