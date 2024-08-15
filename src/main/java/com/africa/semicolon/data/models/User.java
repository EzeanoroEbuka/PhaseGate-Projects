package com.africa.semicolon.data.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.ArrayList;


@Getter
@Setter
@Document("userContact")
@ToString
public class User {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private boolean isLoggedIn;
    @DBRef
    private ArrayList<Contact> contacts = new ArrayList<>();
}
