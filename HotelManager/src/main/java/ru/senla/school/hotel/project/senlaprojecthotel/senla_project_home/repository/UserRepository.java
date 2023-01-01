package ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.repository;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.entity.RoomEntity;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.entity.UserEntity;

import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class UserRepository implements IUserRepository{
    @PersistenceContext
    private Session session;

    public UserEntity findByEmail(String email) {
        Query query = session.createQuery("from UserEntity WHERE email = :email");
        query.setParameter("email", email);
        UserEntity userEntity = (UserEntity) query.getSingleResult();
        return userEntity;
    }
}
