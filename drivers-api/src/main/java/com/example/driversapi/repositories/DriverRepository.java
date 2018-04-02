package com.example.driversapi.repositories;

import com.example.driversapi.models.Driver;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RestController
public interface DriverRepository extends CrudRepository<Driver, Long> {

}

