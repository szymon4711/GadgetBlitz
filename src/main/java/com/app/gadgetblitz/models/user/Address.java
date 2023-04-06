package com.app.gadgetblitz.models.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Address {
    private String city;
    private String number;
    private String street;
    private String zip;
}
