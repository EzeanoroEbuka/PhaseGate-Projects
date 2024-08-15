package com.africa.semicolon.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateContactResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String message;
}
