package ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.repository;

import org.hibernate.Session;
import org.springframework.stereotype.Component;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.entity.RoomEntity;

import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Component
public class RoomRepository implements IRoomRepository {

    @PersistenceContext
    private Session session;

    public void save(RoomEntity room) {
        session.save(room);
    }

    public void saveAll(List<RoomEntity> rooms) {
        rooms.forEach(room -> {
            session.saveOrUpdate(room);
        });
    }

    public List<RoomEntity> findAll() {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<RoomEntity> criteria = builder.createQuery(RoomEntity.class);
        criteria.from(RoomEntity.class);
        return session.createQuery(criteria).getResultList();
    }

    public RoomEntity findByNumber(int number) {
        return session.get(RoomEntity.class, number);
    }

    public int count() {
        return (Integer) session.createQuery("SELECT COUNT(s) from RoomEntity s")
                .getSingleResult();
    }
}
