package com.app.gadgetblitz.service;

import com.app.gadgetblitz.dto.PhoneSimpleDto;
import com.app.gadgetblitz.model.phone.Phone;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface PhoneService {
    List<PhoneSimpleDto> findAll();

    List<PhoneSimpleDto> findBySpecification(String name, String brand,
                                             Double sizeMin, Double sizeMax,
                                             Integer storageMin, Integer storageMax,
                                             BigDecimal priceMin, BigDecimal priceMax,
                                             Integer cameraBackMin, Integer cameraBackMax);

    Optional<Phone> findById(String id);


}
