package com.example.driverapi.repositories;

import com.example.driversapi.models.Driver;
import com.example.driversapi.repositories.DriverRepository;
import com.google.common.collect.Iterables;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DriverRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private DriverRepository driverRepository;

    @Before
    public void setUp() {
        Driver firstDriver = new Driver(
                "MD",
                "5467230",
                "medalion driver",
                "04/15/2018",
                "03/24/2018",
                "13:25"
        );

        Driver secondDriver = new Driver(

                "kamal",
                "5412334",
                "medalion driver",
                "05/12/2018",
                "03/26/2018",
                "14:30"
        );

        entityManager.persist(firstDriver);
        entityManager.persist(secondDriver);
        entityManager.flush();
    }

    @Test
    public void findAll_returnsAllDrivers() {
        Iterable<Driver> driverFromDb = driverRepository.findAll();

        assertThat(Iterables.size(driverFromDb),is(2));
    }


//    @Test
//    public void findAll_returnsDriverName() {
//        Iterable<Driver> driverFromDb = driverRepository.findAll();
//
//        String secondDriversDriverName = Iterables.get(driverFromDb, 1).getName();
//
//        assertThat(secondDriversDriverName, is("md"));
//    }
//
//    @Test
//    public void findAll_returnslicense_number() {
//        Iterable<Driver> driverFromDb = driverRepository.findAll();
//
//        String secondDriverslicense_number = Iterables.get(driverFromDb, 1).getLicense_number();
//
//        assertThat(secondDriverslicense_number, is("1234555"));
//    }
//
//    @Test
//    public void findAll_returnstype() {
//        Iterable<Driver> driverFromDb = driverRepository.findAll();
//
//        String secondDriverstype = Iterables.get(driverFromDb, 1).type();
//
//        assertThat(secondDriverstype, is("medalion driver"));
//    }


}
