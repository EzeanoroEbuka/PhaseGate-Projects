package com.africa.semicolon.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateContactResponse {
     private String firstName;
    private String lastName;
    private String phoneNumber;
    private String message;
}
