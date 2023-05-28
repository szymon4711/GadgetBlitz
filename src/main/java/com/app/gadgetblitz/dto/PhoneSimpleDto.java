package com.app.gadgetblitz.dto;

import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the {@link com.app.gadgetblitz.model.phone.Phone} entity
 */
public record PhoneSimpleDto(
        String id,
        String name,
        Double size,
//        String procesor,

        Integer battery,
        String system,
        Integer storage,
        List<String> urls,
        Double price

) implements Serializable {}