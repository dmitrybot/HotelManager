package ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.repository.IGuestRepository;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.repository.IRoomRepository;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.repository.IServiceInfoRepository;

import static org.mockito.Mockito.mock;

@Configuration
public class TestConfig {

    @Bean
    public IServiceInfoRepository serviceInfoRepository(){
        return mock(IServiceInfoRepository.class);
    }

    @Bean
    public IRoomRepository roomRepository(){
        return mock(IRoomRepository.class);
    }

    @Bean
    public IGuestRepository guestRepository(){
        return mock(IGuestRepository.class);
    }
}