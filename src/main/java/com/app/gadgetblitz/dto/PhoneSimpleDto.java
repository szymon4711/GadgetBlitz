package com.app.gadgetblitz.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * A DTO for the {@link com.app.gadgetblitz.model.phone.Phone} entity
 */
public record PhoneSimpleDto(
        String id,
        String name,
        Double size,
        String procesor,
        String system,
        Integer storage,
        List<String> urls,
        BigDecimal price

) implements Serializable {}