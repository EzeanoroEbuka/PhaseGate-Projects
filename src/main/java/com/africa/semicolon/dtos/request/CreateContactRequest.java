package com.africa.semicolon.dtos.request;

import com.africa.semicolon.data.models.User;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateContactRequest {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String userEmail;

}
