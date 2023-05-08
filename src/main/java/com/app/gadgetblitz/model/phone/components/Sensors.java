package com.app.gadgetblitz.model.phone.components;

public record Sensors(
        Boolean has_barometer,
        Boolean has_gyroscope,
        Boolean has_accelerometer,
        Boolean has_proximity_sensor,
        Boolean has_ambient_light_sensor
) {}
