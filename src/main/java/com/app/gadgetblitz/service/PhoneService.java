package com.app.gadgetblitz.service;

import com.app.gadgetblitz.dto.PhoneFullDto;
import com.app.gadgetblitz.dto.PhoneSimpleDto;
import com.app.gadgetblitz.model.phone.Opinion;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface PhoneService {
    Page<PhoneSimpleDto> findAll(Integer page, Integer size);

    Page<PhoneSimpleDto> findBySpecification(String name, String brand,
                                             Double sizeMin, Double sizeMax,
                                             Integer storageMin, Integer storageMax,
                                             Double priceMin, Double priceMax,
                                             Integer batteryMin, Integer batteryMax,
                                             String system,
                                             Integer page, Integer size);

    Optional<PhoneFullDto> findById(String id);

    PhoneFullDto addOpinion(String id, Opinion opinion);
}
