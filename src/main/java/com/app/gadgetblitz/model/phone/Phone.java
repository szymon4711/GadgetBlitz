package com.app.gadgetblitz.model.phone;

import com.app.gadgetblitz.model.phone.components.Image;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@lombok.Data
@Document("phones")
public class Phone {

    @Id
    private String id;

    private String name;

    private Data data;

    private List<Image> images;

    @Field("avgPrice")
    private Double price;

    private List<String> opinions;

}
