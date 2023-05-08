package com.app.gadgetblitz.model.phone.components;

public record Navigation(
        Boolean has_gps,
        Boolean has_a_gps,
        Boolean has_beidou,
        Boolean has_galileo,
        Boolean has_glonass,
        Boolean has_electronic_compass
) {}
