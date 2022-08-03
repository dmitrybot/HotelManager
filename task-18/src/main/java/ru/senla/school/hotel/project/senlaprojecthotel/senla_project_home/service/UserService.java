package ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.service;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.entity.UserEntity;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.repository.IUserRepository;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.security.MyUserDetailsService;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.security.SecurityUser;

import javax.persistence.NoResultException;

@Service
public class UserService implements IUserService {

    private final IUserRepository userRepository;

    private static final Logger log = Logger.getLogger(MyUserDetailsService.class);

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(timeout = 10)
    public UserEntity findByEmail(String email){
        try {
            UserEntity user = userRepository.findByEmail(email);
            return user;
        } catch (HibernateException | NoResultException | NullPointerException e) {
            log.error("db user find failed");
            throw new UsernameNotFoundException("User doesn't exists");
        }
    }
}
