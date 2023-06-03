package com.app.gadgetblitz.dto;

import com.app.gadgetblitz.model.phone.Data;
import com.app.gadgetblitz.model.phone.Opinion;

import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the {@link com.app.gadgetblitz.model.phone.Phone} entity
 */
public record PhoneFullDto(
        String id,
        String name,
        Data data,
        List<String> urls,
        Double price,
        List<Opinion> opinions,
        Double rating

) implements Serializable {}
