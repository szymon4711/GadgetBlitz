package com.app.gadgetblitz.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("phones")
@AllArgsConstructor
public class Phone {
    @Id
    private String id;
    private String CPU;
}
