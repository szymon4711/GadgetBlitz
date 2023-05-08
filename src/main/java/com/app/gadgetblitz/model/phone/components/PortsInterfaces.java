package com.app.gadgetblitz.model.phone.components;

public record PortsInterfaces(
        Boolean has_lightning_connector,
        Boolean has_usb,
        String type_of_connector
) {}
