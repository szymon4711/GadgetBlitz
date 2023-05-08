package com.app.gadgetblitz.model.phone;

import com.app.gadgetblitz.model.phone.components.*;
import org.springframework.data.mongodb.core.mapping.Field;

@lombok.Data
public class Data {
    private Battery battery;
    private Camera camera;
    private Cpu cpu;
    private Design design;
    private Display display;
    private General general;
    private Measurements measurements;
    private Navigation navigation;
    private Network network;
    @Field("ports_interfaces")
    private PortsInterfaces portsInterfaces;
    private Sensors sensors;
    private Software software;
    private Storage storage;
    private Other other;
}
