package ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.service;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.constants.ProductSortType;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.entity.ProductEntity;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.exeption.DBExeption;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.repository.IRoomRepository;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.repository.IServiceInfoRepository;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelService implements IHotelService {

    IRoomRepository roomRepository;
    IServiceInfoRepository serviceInfoRepository;

    private static final Logger log = Logger.getLogger(HotelService.class);

    public HotelService(IRoomRepository roomRepository, IServiceInfoRepository serviceInfoRepository) {
        this.roomRepository = roomRepository;
        this.serviceInfoRepository = serviceInfoRepository;
    }

    @Transactional(timeout = 10)
    public List<ProductEntity> getSortedProducts(ProductSortType productSortType) throws DBExeption {
        try {
            List<ProductEntity> products = new ArrayList<>();
            products.addAll(roomRepository.findAll());
            products.addAll(serviceInfoRepository.findAll());

            Comparator<ProductEntity> comparator = Comparator.comparing(obj -> obj.getPrice());
            switch (productSortType) {
                case PRICE:
                    comparator = Comparator.comparing(obj -> obj.getPrice());
                    break;
                case CHAPTER:
                    comparator = Comparator.comparing(obj -> obj.getChapter());
                    break;
                default:
                    break;
            }
            return products.stream().sorted(comparator).collect(Collectors.toList());
        } catch (HibernateException | NoResultException | NullPointerException e) {
            log.error("db products get operation failed");
            throw new DBExeption();
        }
    }
}
