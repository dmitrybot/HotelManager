package ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.config;

import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource("classpath:/room.properties")
@ComponentScan("ru.senla.school.hotel.project.senlaprojecthotel")
@Import({ SecurityConfig.class })
@EnableTransactionManagement
public class MyApplicationContextConfiguration {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean em
                = new LocalSessionFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(new String[]{"ru.senla.school.hotel.project.senlaprojecthotel"});

        em.setHibernateProperties(additionalProperties());

        return em;
    }

    Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL95Dialect");
        properties.setProperty("hibernate.current_session_context_class", "thread");
        properties.setProperty("hibernate.hbm2ddl.auto", "update");

        return properties;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());

        return transactionManager;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/senla_hotel");
        dataSource.setUsername("postgres");
        dataSource.setPassword("dinesh1329");
        return dataSource;
    }
}
