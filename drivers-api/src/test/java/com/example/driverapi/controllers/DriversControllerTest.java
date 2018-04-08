package com.example.driverapi.controllers;


import com.example.driversapi.controllers.DriversController;
import com.example.driversapi.models.Driver;
import com.example.driversapi.repositories.DriverRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doAnswer;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration
@SpringBootConfiguration
@RunWith(SpringRunner.class)
@WebMvcTest(DriversController.class)
public class DriversControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private Driver newDriver;

    private Driver updatedSecondDriver;


    @Autowired
    private ObjectMapper jsonObjectMapper;


    @MockBean
    private DriverRepository mockDriverRepository;

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

        newDriver = new Driver(
                "neuon",
                "4455555",
                "medalion driver",
                "08/13/2019",
                "03/29/2018",
                "16:20"
        );
        given(mockDriverRepository.save(newDriver)).willReturn(newDriver);

        updatedSecondDriver = new Driver(
                "kamal",
                "5412334",
                "medalion driver",
                "05/12/2018",
                "03/26/2018",
                "14:30"

        );
        given(mockDriverRepository.save(updatedSecondDriver)).willReturn(updatedSecondDriver);

        Iterable<Driver> mockDrivers =
                Stream.of(firstDriver, secondDriver).collect(Collectors.toList());

        given(mockDriverRepository.findAll()).willReturn(mockDrivers);
        given(mockDriverRepository.findOne(1L)).willReturn(firstDriver);
        given(mockDriverRepository.findOne(4L)).willReturn(null);
        doAnswer(invocation -> {
            throw new EmptyResultDataAccessException("ERROR MESSAGE FROM MOCK!!!", 1234);
        }).when(mockDriverRepository).delete(4L);


    }

    @Test
    public void findAllDrivers_success_returnsStatusOK() throws Exception {

        this.mockMvc
                .perform(get("/"))
                .andExpect(status().isOk());
    }


//    @Test
//    public void findAllDrivers_success_returnAllDriversAsJSON() throws Exception {
//
//        this.mockMvc
//                .perform(get("/"))
//                .andExpect(jsonPath("$", hasSize(2)));
//    }
//
//    @Test
//    public void findAllDrivers_success_returnDriverNameForEachDriver() throws Exception {
//
//        this.mockMvc
//                .perform(get("/"))
//                .andExpect(jsonPath("$[0].name", is("MD")));
//    }
//
//    @Test
//    public void findAllDrivers_success_returnFirstNameForEachDriver() throws Exception {
//
//        this.mockMvc
//                .perform(get("/"))
//                .andExpect(jsonPath("$[0].license_number", is("5467230")));
//    }
//
//    @Test
//    public void findAllDrivers_success_returnLastNameForEachDriver() throws Exception {
//
//        this.mockMvc
//                .perform(get("/"))
//                .andExpect(jsonPath("$[0].type", is("medalion driver")));
//    }
//
//    @Test
//    public void findDriverById_success_returnsStatusOK() throws Exception {
//
//        this.mockMvc
//                .perform(get("/1"))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void findDriverById_success_returnDriverName() throws Exception {
//
//        this.mockMvc
//                .perform(get("/1"))
//                .andExpect(jsonPath("$.name", is("MD")));
//    }
//
//    @Test
//    public void findDriverById_success_returnlicense_number() throws Exception {
//
//        this.mockMvc
//                .perform(get("/1"))
//                .andExpect(jsonPath("$.license_number", is("5467230")));
//    }
//
//    @Test
//    public void findDriverById_success_returnLastName() throws Exception {
//
//        this.mockMvc
//                .perform(get("/1"))
//                .andExpect(jsonPath("$.type", is("medalion driver")));
//    }
//
//    @Test
//    public void findDriverById_failure_driverNotFoundReturns404() throws Exception {
//
//        this.mockMvc
//                .perform(get("/4"))
//                .andExpect(status().reason(containsString("Driver with ID of 4 was not found!")));
//    }
//
//    @Test
//    public void deleteDriverById_success_returnsStatusOk() throws Exception {
//
//        this.mockMvc
//                .perform(delete("/1"))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void deleteDriverById_success_deletesViaRepository() throws Exception {
//
//        this.mockMvc.perform(delete("/1"));
//
//        verify(mockDriverRepository, times(1)).delete(1L);
//    }
//
//    @Test
//    public void deleteDriverById_failure_driverNotFoundReturns404() throws Exception {
//
//        this.mockMvc
//                .perform(delete("/4"))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    public void createDriver_success_returnsStatusOk() throws Exception {
//
//        this.mockMvc
//                .perform(
//                        post("/")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(jsonObjectMapper.writeValueAsString(newDriver))
//                )
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void createDriver_success_returnsDriverName() throws Exception {
//
//        this.mockMvc
//                .perform(
//                        post("/")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(jsonObjectMapper.writeValueAsString(newDriver))
//                )
//                .andExpect(jsonPath("$.driverName", is("new_driver_for_create")));
//    }
//
//    @Test
//    public void createDriver_success_returnsName() throws Exception {
//
//        this.mockMvc
//                .perform(
//                        post("/")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(jsonObjectMapper.writeValueAsString(newDriver))
//                )
//                .andExpect(jsonPath("$.name", is("new_driver_for_create")));
//    }
//
//    @Test
//    public void createDriver_success_returnslicense_number() throws Exception {
//
//        this.mockMvc
//                .perform(
//                        post("/")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(jsonObjectMapper.writeValueAsString(newDriver))
//                )
//                .andExpect(jsonPath("$.license_number", is("123456")));
//    }
//
//    @Test
//    public void updateDriverById_success_returnsStatusOk() throws Exception {
//
//        this.mockMvc
//                .perform(
//                        patch("/1")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(jsonObjectMapper.writeValueAsString(updatedSecondUser))
//                )
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void updateDriverById_success_returnsUpdatedDriverName() throws Exception {
//
//        this.mockMvc
//                .perform(
//                        patch("/1")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(jsonObjectMapper.writeValueAsString(updatedSecondUser))
//                )
//                .andExpect(jsonPath("$.driverName", is("updated_drivername")));
//    }
//
//    @Test
//    public void updateDriverById_success_returnsUpdatedFirstName() throws Exception {
//
//        this.mockMvc
//                .perform(
//                        patch("/1")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(jsonObjectMapper.writeValueAsString(updatedSecondUser))
//                )
//                .andExpect(jsonPath("$.name", is("Updated")));
//    }
//
//    @Test
//    public void updateDriverById_success_returnsUpdatedLastName() throws Exception {
//
//        this.mockMvc
//                .perform(
//                        patch("/1")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(jsonObjectMapper.writeValueAsString(updatedSecondUser))
//                )
//                .andExpect(jsonPath("$.license_number", is("Info")));
//    }
//
//    @Test
//    public void updateDriverById_failure_driverNotFoundReturns404() throws Exception {
//
//        this.mockMvc
//                .perform(
//                        patch("/4")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(jsonObjectMapper.writeValueAsString(updatedSecondUser))
//                )
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    public void updateDriverById_failure_driverNotFoundReturnsNotFoundErrorMessage() throws Exception {
//
//        this.mockMvc
//                .perform(
//                        patch("/4")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(jsonObjectMapper.writeValueAsString(updatedSecondUser))
//                )
//                .andExpect(status().reason(containsString("Driver with ID of 4 was not found!")));
//    }
//
//
//
//
}

