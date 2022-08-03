package ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.config.MyApplicationContextConfiguration;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@ContextConfiguration(classes = { MyApplicationContextConfiguration.class }, loader = AnnotationConfigWebContextLoader.class)
@WebAppConfiguration("src/test/webapp")
public abstract class AbstractUtilTest {
}
