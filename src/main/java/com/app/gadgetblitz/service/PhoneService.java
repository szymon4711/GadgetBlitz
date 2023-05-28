package com.app.gadgetblitz.service;

import com.app.gadgetblitz.dto.PhoneFullDto;
import com.app.gadgetblitz.dto.PhoneSimpleDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface PhoneService {
    Page<PhoneSimpleDto> findAll(Integer page, Integer size);

    Page<PhoneSimpleDto> findBySpecification(String name, String brand,
                                             Double sizeMin, Double sizeMax,
                                             Integer storageMin, Integer storageMax,
                                             Double priceMin, Double priceMax,
                                             Integer cameraBackMin, Integer cameraBackMax);

    Optional<PhoneFullDto> findById(String id);
}
