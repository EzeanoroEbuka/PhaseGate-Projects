package com.africa.semicolon.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GetUserContacts {
    private String userEmail;
    private String contactNumber;
}
