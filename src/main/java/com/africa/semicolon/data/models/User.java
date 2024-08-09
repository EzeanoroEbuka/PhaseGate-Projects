package com.africa.semicolon.data.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document
public class User {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
}
