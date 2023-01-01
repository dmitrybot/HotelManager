package ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.constants.RoomSortType;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.constants.Status;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.dto.GuestPutIntoDTO;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.entity.GuestEntity;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.entity.RoomEntity;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.exeption.CantChangeRoomStatusExeption;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.exeption.DBExeption;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.service.IGuestService;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.service.IRoomService;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.util.IMappingUtils;

import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {

    IRoomService roomService;
    IGuestService guestService;
    private IMappingUtils mappingUtils;

    public RoomController(IRoomService roomService, IGuestService guestService, IMappingUtils mappingUtils) {
        this.roomService = roomService;
        this.guestService = guestService;
        this.mappingUtils = mappingUtils;
    }

    @PutMapping(value = "/{number}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void putInRoom(@RequestBody GuestPutIntoDTO guest, @PathVariable("number") int number) throws DBExeption {
        GuestEntity g = mappingUtils.mapToGuestEntity(guest);
        g.setRoom(roomService.putInRoom(g, number));
        g.setIn(true);
        guestService.addGuest(g);
    }

    @PutMapping(value = "/evict/{number}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void evictFromRoom(@PathVariable("number") int number) throws DBExeption {
        roomService.evictFromRoom(number);
    }

    @PutMapping(value = "/status/{number}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void changeStatus(@PathVariable("number") int number, @RequestParam(name = "status", required = false) Status status) throws CantChangeRoomStatusExeption, DBExeption, CantChangeRoomStatusExeption {
        roomService.changeStatus(number, status);
    }

    @PostMapping(value = "/changeprice/{number}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void changePriceForRoom(@PathVariable("number") int number, @RequestParam(name = "price", required = false) int price) throws DBExeption {
        roomService.changePriceForRoom(number, price);
    }

    @PutMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public void addRoom(@RequestBody RoomEntity room) throws DBExeption {
        roomService.addRoom(room);
    }

    @GetMapping(value = "/sort")
    public List<RoomEntity> sortRooms(@RequestParam(name = "sort", required = false) RoomSortType roomSortType) throws DBExeption {
        return roomService.sortRooms(roomSortType);
    }

    @GetMapping(value = "/sortempty", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RoomEntity> sortEmptyRooms(@RequestParam(name = "sort", required = false) RoomSortType roomSortType) throws DBExeption {
        return roomService.sortEmptyRooms(roomSortType);
    }

    @GetMapping(value = "/dateempty", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RoomEntity> getDateEmptyRooms(@RequestParam(name = "date", required = false) String date) throws DBExeption {
        return roomService.getDateEmptyRooms(date);
    }

    @GetMapping(value = "/countempty", produces = MediaType.APPLICATION_JSON_VALUE)
    public int getEmptyRoomCount() throws DBExeption {
        return roomService.getEmptyRoomCount();
    }

    @GetMapping(value = "/threeguests/{number}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GuestEntity> showThreeLastGuests(@PathVariable("number") int number) throws DBExeption {
        return roomService.getThreeLastGuest(number);
    }

    @GetMapping(value = "/{number}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RoomEntity showRoomDetails(@PathVariable("number") int number) throws DBExeption {
        return roomService.findRoom(number);
    }
}
