package com.shilovi.drones.rest.controller;

import com.shilovi.drones.model.DroneDto;
import com.shilovi.drones.model.ErrorResponse;
import com.shilovi.drones.model.base.DroneModel;
import com.shilovi.drones.service.drones.DronesService;
import com.shilovi.drones.utils.JsonHelper;
import com.shilovi.drones.utils.tag.SpringIntegrationTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringIntegrationTest
class DronesControllerTest {

    private AutoCloseable closeable;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DronesController dronesController;

    @Mock
    private DronesService service;

    @Captor
    ArgumentCaptor<DroneDto> captor;

    private DroneDto responseDto;


    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        responseDto = DroneDto.builder()
                .serialNumber("responseDrone")
                .model("Cruiserweight")
                .build();
        when(service.register(any())).thenReturn(responseDto);
        ReflectionTestUtils.setField(dronesController, "service", service);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void register() throws Exception {
        String drone = """
                {
                "serialNumber" : "serialNumber",
                "weightLimit" : 400,
                "model" : "Cruiserweight"
                }
                """;
        MvcResult result = mockMvc.perform(post("/drones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(drone))
                .andExpect(status().isOk())
                .andReturn();
        verify(service).register(captor.capture());
        DroneDto dto = captor.getValue();
        assertEquals("serialNumber", dto.getSerialNumber());
        assertEquals(JsonHelper.getObjectFromString(result.getResponse().getContentAsString(), DroneDto.class),
                responseDto);
    }

    @Test
    void registerBadRequest() throws Exception {
        String drone = """
                {
                "serialNumber" : "serialNumberNew",
                "weightLimit" : 700,
                "model" : "Cruiserweight"
                }
                """;
        mockMvc.perform(post("/drones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(drone))
                .andExpect(status().isBadRequest())
                .andReturn();
        verify(service, never()).register(any());
    }


    @Test
    void check() {
    //same test as above
    }
}