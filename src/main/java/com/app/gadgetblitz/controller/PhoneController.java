package com.app.gadgetblitz.controller;

import com.app.gadgetblitz.dto.PhoneSimpleDto;
import com.app.gadgetblitz.model.phone.Phone;
import com.app.gadgetblitz.service.PhoneService;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api/phones")
public class PhoneController {

    MongoTemplate mongoTemplate;
    PhoneService phoneService;

    @GetMapping()
    public ResponseEntity<List<PhoneSimpleDto>> getAllPhones() {
        List<PhoneSimpleDto> phones = phoneService.findAll();
        if (phones.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(phones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Phone>> getPhoneById(@PathVariable String id) {
        Optional<Phone> phone = phoneService.findById(id);
        if (phone.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(phone);
    }

    @GetMapping("search")
    public ResponseEntity<List<PhoneSimpleDto>> getPhonesBySpecification(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) Double sizeMin,
            @RequestParam(required = false) Double sizeMax,
            @RequestParam(required = false) Integer storageMin,
            @RequestParam(required = false) Integer storageMax,
            @RequestParam(required = false) BigDecimal priceMin,
            @RequestParam(required = false) BigDecimal priceMax,
            @RequestParam(required = false) Integer cameraBackMin,
            @RequestParam(required = false) Integer cameraBackMax) {


        List<PhoneSimpleDto> phones = phoneService.findBySpecification(name, brand, sizeMin, sizeMax, storageMin,
                storageMax, priceMin, priceMax, cameraBackMin, cameraBackMax);

        if (phones.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(phones);
    }


}
