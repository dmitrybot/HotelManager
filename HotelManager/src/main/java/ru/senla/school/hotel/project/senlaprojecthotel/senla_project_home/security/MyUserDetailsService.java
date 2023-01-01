package ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.security;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.entity.UserEntity;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.repository.IUserRepository;

import javax.persistence.NoResultException;

@Service("userDetailsServiceImpl")
public class MyUserDetailsService implements UserDetailsService {

    private final IUserRepository userRepository;

    private static final Logger log = Logger.getLogger(MyUserDetailsService.class);

    public MyUserDetailsService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(timeout = 10)
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            UserEntity user = userRepository.findByEmail(email);
            return SecurityUser.fromUser(user);
        } catch (HibernateException | NoResultException | NullPointerException e) {
            log.error("db user find failed");
            throw new UsernameNotFoundException("User doesn't exists");
        }
    }
}