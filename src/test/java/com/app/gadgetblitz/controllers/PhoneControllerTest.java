package com.app.gadgetblitz.controllers;

import com.app.gadgetblitz.controller.PhoneController;
import com.app.gadgetblitz.dto.PhoneFullDto;
import com.app.gadgetblitz.dto.PhoneSimpleDto;
import com.app.gadgetblitz.service.PhoneService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PhoneControllerTest {

    @Mock
    private PhoneService phoneService;

    @InjectMocks
    private PhoneController phoneController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllPhones() {
        // Prepare
        Integer page = 0;
        Integer size = 10;
        List<PhoneSimpleDto> phones = new ArrayList<>();
        phones.add(new PhoneSimpleDto("1", "Phone 1", 5.5, "Processor 1", "OS 1", 64, new ArrayList<>(), 999.99));
        phones.add(new PhoneSimpleDto("2", "Phone 2", 6.0, "Processor 2", "OS 2", 128, new ArrayList<>(), 1499.99));
        when(phoneService.findAll(page, size)).thenReturn(phones);

        // Execute
        ResponseEntity<List<PhoneSimpleDto>> response = phoneController.getAllPhones(page, size);

        // Verify
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(phones, response.getBody());
        verify(phoneService, times(1)).findAll(page, size);
    }

    @Test
    void testGetAllPhones_NoPhonesFound() {
        // Prepare
        Integer page = 0;
        Integer size = 10;
        List<PhoneSimpleDto> phones = new ArrayList<>();
        when(phoneService.findAll(page, size)).thenReturn(phones);

        // Execute
        ResponseEntity<List<PhoneSimpleDto>> response = phoneController.getAllPhones(page, size);

        // Verify
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(phoneService, times(1)).findAll(page, size);
    }

    @Test
    void testGetPhoneById() {
        // Prepare
        String id = "1";
        PhoneFullDto phone = new PhoneFullDto("1", "Phone 1", null, new ArrayList<>(), 999.99);
        when(phoneService.findById(id)).thenReturn(Optional.of(phone));

        // Execute
        ResponseEntity<Optional<PhoneFullDto>> response = phoneController.getPhoneById(id);

        // Verify
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Optional.of(phone), response.getBody());
        verify(phoneService, times(1)).findById(id);
    }

    @Test
    void testGetPhoneById_PhoneNotFound() {
        // Prepare
        String id = "1";
        when(phoneService.findById(id)).thenReturn(Optional.empty());

        // Execute
        ResponseEntity<Optional<PhoneFullDto>> response = phoneController.getPhoneById(id);

        // Verify
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(phoneService, times(1)).findById(id);
    }

    @Test
    void testGetPhonesBySpecification() {
        // Prepare
        String name = "Phone";
        String brand = "Brand";
        Double sizeMin = 5.0;
        Double sizeMax = 6.5;
        Integer storageMin = 64;
        Integer storageMax = 128;
        Double priceMin = 500.0;
        Double priceMax = 1500.0;
        Integer cameraBackMin = 12;
        Integer cameraBackMax = 20;
        List<PhoneSimpleDto> phones = new ArrayList<>();
        phones.add(new PhoneSimpleDto("1", "Phone 1", 5.5, "Processor 1", "OS 1", 64, new ArrayList<>(), 999.99));
        phones.add(new PhoneSimpleDto("2", "Phone 2", 6.0, "Processor 2", "OS 2", 128, new ArrayList<>(), 1499.99));
        when(phoneService.findBySpecification(name, brand, sizeMin, sizeMax, storageMin, storageMax, priceMin, priceMax, cameraBackMin, cameraBackMax)).thenReturn(phones);

        // Execute
        ResponseEntity<List<PhoneSimpleDto>> response = phoneController.getPhonesBySpecification(name, brand, sizeMin, sizeMax, storageMin, storageMax, priceMin, priceMax, cameraBackMin, cameraBackMax);

        // Verify
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(phones, response.getBody());
        verify(phoneService, times(1)).findBySpecification(name, brand, sizeMin, sizeMax, storageMin, storageMax, priceMin, priceMax, cameraBackMin, cameraBackMax);
    }

    @Test
    void testGetPhonesBySpecification_NoPhonesFound() {
        // Prepare
        String name = "Phone";
        String brand = "Brand";
        Double sizeMin = 5.5;
        Double sizeMax = 6.5;
        Integer storageMin = 64;
        Integer storageMax = 128;
        Double priceMin = 500.0;
        Double priceMax = 1500.0;
        Integer cameraBackMin = 12;
        Integer cameraBackMax = 20;
        List<PhoneSimpleDto> phones = new ArrayList<>();
        when(phoneService.findBySpecification(name, brand, sizeMin, sizeMax, storageMin, storageMax, priceMin, priceMax, cameraBackMin, cameraBackMax)).thenReturn(phones);

        // Execute
        ResponseEntity<List<PhoneSimpleDto>> response = phoneController.getPhonesBySpecification(name, brand, sizeMin, sizeMax, storageMin, storageMax, priceMin, priceMax, cameraBackMin, cameraBackMax);

        // Verify
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(phoneService, times(1)).findBySpecification(name, brand, sizeMin, sizeMax, storageMin, storageMax, priceMin, priceMax, cameraBackMin, cameraBackMax);
    }
}