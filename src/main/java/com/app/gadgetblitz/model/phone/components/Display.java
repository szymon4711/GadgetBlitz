package com.app.gadgetblitz.model.phone.components;

public record Display(
        String type,
        String shape,
        Double size__inch,
        String contrast_ratio,
        Integer brightness__cdm,
        Boolean has_touchscreen,
        Integer refresh_rate__hz,
        Integer pixel_density__ppi,
        String resolution__pixels,
        Boolean fingerprint_resistant,
        Boolean has_gorilla_glass
) {}
