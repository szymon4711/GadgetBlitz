package com.app.gadgetblitz.controller;

import com.app.gadgetblitz.dto.PhoneFullDto;
import com.app.gadgetblitz.dto.PhoneSimpleDto;
import com.app.gadgetblitz.service.PhoneService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

//    @GetMapping()
//    public ResponseEntity<List<PhoneSimpleDto>> getAllPhones() {
//        List<PhoneSimpleDto> phones = phoneService.findAll();
//        if (phones.isEmpty())
//            return ResponseEntity.notFound().build();
//        return ResponseEntity.ok(phones);
//    }

    @GetMapping()
    public ResponseEntity<List<PhoneSimpleDto>> getAllPhones(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        
        List<PhoneSimpleDto> phonesPage = phoneService.findAll(page, size);

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
