package com.app.gadgetblitz.dto;

import com.app.gadgetblitz.model.phone.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * A DTO for the {@link com.app.gadgetblitz.model.phone.Phone} entity
 */
public record PhoneFullDto(
        String id,
        String name,
        Data data,
        List<String> urls,
        BigDecimal price
) implements Serializable {}
