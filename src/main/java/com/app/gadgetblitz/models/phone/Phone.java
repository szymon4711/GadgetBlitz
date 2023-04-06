package com.app.gadgetblitz.models.phone;

import com.app.gadgetblitz.models.phone.components.Battery;
import com.app.gadgetblitz.models.phone.components.Camera;
import com.app.gadgetblitz.models.phone.components.Display;
import com.app.gadgetblitz.models.phone.components.Storage;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;

@Data
@Document("phones")
public class Phone {

    @Id
    private String id;
    @Field("CPU")
    private String cpu;
    @Field("IPX")
    private String ipx;
    private Battery battery;
    private String brand;
    @Field("camera.front")
    private Camera frontCamera;
    @Field("camera.rear")
    private Camera rearCamera;
    private String colors;
    private Display display;
    private String model;
    private int price; // TODO price should be BigDecimal
    private Storage storage;
    private int year; //TODO this should be date

}
