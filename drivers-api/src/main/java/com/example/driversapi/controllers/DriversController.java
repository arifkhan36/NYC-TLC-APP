package com.example.driversapi.controllers;


import com.example.driversapi.models.Driver;
import com.example.driversapi.repositories.DriverRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.example.driversapi.models.Driver;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class DriversController {

    @Autowired
    private DriverRepository driverRepository;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/")
    public Iterable<Driver> findAllDrivers() {
        return driverRepository.findAll();
    }

    @GetMapping("/{driverId}")
    public Driver findDriverById(@PathVariable Long driverId) throws NotFoundException {

        Driver foundDriver = driverRepository.findOne(driverId);

       if (foundDriver == null) {
           throw new NotFoundException("Driver with ID of " + driverId + " was not found!");
       }


       return foundDriver;
    }

    @DeleteMapping("/{driverId}")
    public HttpStatus deleteDriverById(@PathVariable Long driverId) throws EmptyResultDataAccessException {
        driverRepository.delete(driverId);
        return HttpStatus.OK;
   }

    @PostMapping("/")
    public Driver createNewDriver(@RequestBody Driver newDriver) {
       return driverRepository.save(newDriver);
     }

    @ExceptionHandler
    void handleUserNotFound(
            NotFoundException exception,
            HttpServletResponse response) throws IOException {

        response.sendError(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }

    @ExceptionHandler
    void handleDeleteNotFoundException(
            EmptyResultDataAccessException exception,
            HttpServletResponse response) throws IOException {

        response.sendError(HttpStatus.NOT_FOUND.value());
   }
}
