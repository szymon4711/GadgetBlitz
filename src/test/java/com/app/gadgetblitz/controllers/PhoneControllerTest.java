package com.app.gadgetblitz.controllers;

import com.app.gadgetblitz.models.phone.Phone;
import com.app.gadgetblitz.repository.PhoneRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PhoneControllerTest {

    @Mock
    private PhoneRepository phoneRepository;

    @Mock
    private MongoTemplate mongoTemplate;

    @InjectMocks
    private PhoneController phoneController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllPhones() {
        // Mock data
        List<Phone> phones = new ArrayList<>();
        phones.add(new Phone());
        phones.add(new Phone());

        // Mock repository behavior
        when(phoneRepository.findAll()).thenReturn(phones);

        // Call the controller method
        List<Phone> result = phoneController.getAllPhones();

        // Verify the result
        assertEquals(2, result.size());
        verify(phoneRepository, times(1)).findAll();
        verifyNoMoreInteractions(phoneRepository);
    }

    @Test
    public void testGetPhoneById() {
        // Mock data
        String phoneId = "123";
        Phone phone = new Phone();
        phone.setId(phoneId);
        Optional<Phone> optionalPhone = Optional.of(phone);

        // Mock repository behavior
        when(phoneRepository.findById(phoneId)).thenReturn(optionalPhone);

        // Call the controller method
        Optional<Phone> result = phoneController.getPhoneById(phoneId);

        // Verify the result
        assertEquals(phoneId, result.get().getId());
        verify(phoneRepository, times(1)).findById(phoneId);
        verifyNoMoreInteractions(phoneRepository);
    }

    @Test
    public void testGetPhone() {
        // Mock data
        String marka = "Samsung";
        String model = "Galaxy";
        int price = 1000;
        List<Phone> phones = new ArrayList<>();
        phones.add(new Phone());

        // Mock template behavior
        Criteria criteria = Criteria.where("brand").is(marka)
                .and("model").is(model)
                .and("price").lt(price);
        Query query = new Query(criteria);
        when(mongoTemplate.find(query, Phone.class)).thenReturn(phones);

        // Call the controller method
        List<Phone> result = phoneController.getPhone(marka, model, price);

        // Verify the result
        assertEquals(1, result.size());
        verify(mongoTemplate, times(1)).find(query, Phone.class);
        verifyNoMoreInteractions(mongoTemplate);
    }
}