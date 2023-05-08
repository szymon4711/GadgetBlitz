package com.app.gadgetblitz.model.phone;

import com.app.gadgetblitz.model.phone.components.Image;
import com.app.gadgetblitz.model.phone.components.Price;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@lombok.Data
@Document("phones")
public class Phone {

    @Id
    private String id;

    private String name;

    private Data data;

    private List<Image> images;

    private List<Price> prices;

}
