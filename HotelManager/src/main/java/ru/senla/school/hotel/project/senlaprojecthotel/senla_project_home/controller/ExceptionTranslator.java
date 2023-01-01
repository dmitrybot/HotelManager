package ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.exeption.DBExeption;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.exeption.FindByIdException;

import java.util.Arrays;

@ControllerAdvice
public class ExceptionTranslator {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        Class<?> type = e.getRequiredType();
        String message;
        if (type.isEnum()) {
            message = "The parameter " + e.getName() + " must have a value among : " + Arrays.stream(type.getEnumConstants()).toArray().toString();
        } else {
            message = "The parameter " + e.getName() + " must be of type " + type.getTypeName();
        }
        return new ResponseEntity<Object>(message, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(DBExeption.class)
    @ResponseBody
    public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(DBExeption e) {
        String message = "Error while finding data in database";
        return new ResponseEntity<Object>(message, HttpStatus.I_AM_A_TEAPOT);
    }

    @ExceptionHandler(FindByIdException.class)
    @ResponseBody
    public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(FindByIdException e) {
        String message = "Error while finding data with this id in database";
        return new ResponseEntity<Object>(message, HttpStatus.I_AM_A_TEAPOT);
    }
}