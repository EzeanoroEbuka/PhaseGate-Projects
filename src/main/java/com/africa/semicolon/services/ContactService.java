package com.africa.semicolon.services;

import com.africa.semicolon.data.models.Contact;
import com.africa.semicolon.dtos.request.CreateContactRequest;
import com.africa.semicolon.dtos.request.UpdateContactRequest;
import com.africa.semicolon.dtos.response.CreateContactResponse;
import com.africa.semicolon.dtos.response.DeleteContactResponse;
import com.africa.semicolon.dtos.response.UpdateContactResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ContactService {
    CreateContactResponse addContact(CreateContactRequest contact);
    UpdateContactResponse updateContact(UpdateContactRequest contact);
    DeleteContactResponse deleteContact(String id);
    void deleteAllContact();
    Contact getContact(String phoneNumber);
    Long totalContacts();
    List<Contact> getAllContacts();
    Contact contactExists(String phoneNumber);
}
