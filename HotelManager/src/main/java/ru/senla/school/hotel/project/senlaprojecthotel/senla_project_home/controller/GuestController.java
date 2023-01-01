package ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.constants.ServiceSortType;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.dto.GuestServiceAddingDTO;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.dto.GuestWithoutServicesDTO;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.exeption.DBExeption;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.exeption.FindByIdException;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.service.IGuestService;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.util.IMappingUtils;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/guest")
public class GuestController {

    private IGuestService guestService;
    private IGuestService guestInfoService;
    private IMappingUtils mappingUtils;

    public GuestController(IGuestService guestService, IGuestService guestInfoService, IMappingUtils mappingUtils) {
        this.guestService = guestService;
        this.guestInfoService = guestInfoService;
        this.mappingUtils = mappingUtils;
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GuestWithoutServicesDTO> sortGuests() throws DBExeption {
        return guestService.sortGuestsIn().stream()
                .map(o -> mappingUtils.mapToGuestWithoutServicesDTO(o))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/count", produces = MediaType.APPLICATION_JSON_VALUE)
    public int getGuestsCount() throws DBExeption {
        return guestService.getGuestsCount();
    }

    @GetMapping(value = "/check/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public int getGuestCheck(@PathVariable("id") int id) throws FindByIdException, DBExeption {
        return guestService.getGuestCheck(id);
    }

    @GetMapping(value = "/services/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GuestServiceAddingDTO> getSortedGuestsServices(@PathVariable("id") int id,
                                                               @RequestParam(name = "sort", required = false) ServiceSortType serviceSortType) throws FindByIdException, DBExeption {
        return guestService.getSortedGuestsServices(id, serviceSortType).stream()
                .map(o -> mappingUtils.mapToGuestServiceAddingDTO(o))
                .collect(Collectors.toList());
    }
}
