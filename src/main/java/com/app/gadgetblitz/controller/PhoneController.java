package com.app.gadgetblitz.controller;

import com.app.gadgetblitz.dto.PhoneFullDto;
import com.app.gadgetblitz.dto.PhoneSimpleDto;
import com.app.gadgetblitz.model.phone.Opinion;
import com.app.gadgetblitz.service.PhoneService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api/phones")
public class PhoneController {

    MongoTemplate mongoTemplate;
    PhoneService phoneService;

    @GetMapping()
    public ResponseEntity<Page<PhoneSimpleDto>> getAllPhones(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "9") Integer size) {
        
        Page<PhoneSimpleDto> phonesPage = phoneService.findAll(page, size);

        if (phonesPage.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(phonesPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<PhoneFullDto>> getPhoneById(@PathVariable String id) {
        Optional<PhoneFullDto> phone = phoneService.findById(id);
        if (phone.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(phone);
    }

    @GetMapping("search")
    public ResponseEntity<Page<PhoneSimpleDto>> getPhonesBySpecification(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) Double sizeMin,
            @RequestParam(required = false) Double sizeMax,
            @RequestParam(required = false) Integer storageMin,
            @RequestParam(required = false) Integer storageMax,
            @RequestParam(required = false) Double priceMin,
            @RequestParam(required = false) Double priceMax,
            @RequestParam(required = false) Integer batteryMin,
            @RequestParam(required = false) Integer batteryMax,
            @RequestParam(required = false) String system,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "9") Integer size) {


        Page<PhoneSimpleDto> phones = phoneService.findBySpecification(name, brand, sizeMin, sizeMax, storageMin,
                storageMax, priceMin, priceMax, batteryMin, batteryMax, system, page, size);

        if (phones.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(phones);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PhoneFullDto> addOpinion(
            @PathVariable String id,
            @RequestBody Opinion opinion) {

        PhoneFullDto phoneDto = phoneService.addOpinion(id, opinion);
        return ResponseEntity.ok(phoneDto);
    }
}
