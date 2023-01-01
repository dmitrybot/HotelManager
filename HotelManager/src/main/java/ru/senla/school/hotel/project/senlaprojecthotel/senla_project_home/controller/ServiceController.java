package ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.dto.GuestServiceAddingDTO;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.entity.ServiceInfoEntity;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.exeption.DBExeption;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.exeption.FindByIdException;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.service.IGuestService;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.service.IServiceInfoService;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.service.IServiceService;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.util.IMappingUtils;

@RestController
@RequestMapping("/service")
public class ServiceController {

    private IServiceService serviceService;
    private IServiceInfoService serviceInfoService;
    private IGuestService guestService;
    private IMappingUtils mappingUtils;

    public ServiceController(IServiceService serviceService, IServiceInfoService serviceInfoService, IGuestService guestService, IMappingUtils mappingUtils) {
        this.serviceService = serviceService;
        this.serviceInfoService = serviceInfoService;
        this.guestService = guestService;
        this.mappingUtils = mappingUtils;
    }

    @PostMapping(value = "/changeprice/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void changePriceForService(@PathVariable("id") int id, @RequestParam(name = "price", required = false) int price) throws DBExeption {
        serviceInfoService.changePriceForService(id, price);
    }

    @PutMapping(value = "/info", produces = MediaType.APPLICATION_JSON_VALUE)
    public void addService(@RequestBody ServiceInfoEntity service) throws DBExeption {
        serviceInfoService.addService(service);
    }

    @PutMapping(value = "/guest/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void registerGuestsService(@RequestBody GuestServiceAddingDTO service, @PathVariable("id") int id) throws FindByIdException, DBExeption {
        serviceService.addService(mappingUtils.mapToServiceEntity(service), id);
        guestService.addServiceToGuest(mappingUtils.mapToServiceEntity(service), id);
    }
}
