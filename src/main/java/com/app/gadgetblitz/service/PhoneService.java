package com.app.gadgetblitz.service;

import com.app.gadgetblitz.dto.PhoneFullDto;
import com.app.gadgetblitz.dto.PhoneSimpleDto;

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

    Optional<PhoneFullDto> findById(String id);
}
