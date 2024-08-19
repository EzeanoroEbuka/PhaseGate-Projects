package com.africa.semicolon.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateContactRequest {
    private String newFirstName;
    private String newLastName;
    private String oldPhoneNumber;
    private String newPhoneNumber;
    private String userEmail;
}
