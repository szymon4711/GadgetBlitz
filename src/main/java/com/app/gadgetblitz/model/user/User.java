package com.app.gadgetblitz.model.user;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document("users")
public class User {
    @Id
    private String id;
    private Address address;
    private Date created_at;
    private String email;
    private Name name;
    private Date updated_at;
    private Date date_of_birth;
    private String username;
    private String password;
    private String phone;
}