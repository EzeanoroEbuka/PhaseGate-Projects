package com.africa.semicolon.dtos.response;

import com.africa.semicolon.data.models.Contact;
import lombok.Getter;
import lombok.Setter;


import java.util.List;

@Setter
@Getter
public class ListOfContacts {
    private List<Contact> contacts;
}
