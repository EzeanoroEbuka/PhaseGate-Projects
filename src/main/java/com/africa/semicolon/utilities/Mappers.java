package com.africa.semicolon.utilities;

import com.africa.semicolon.data.models.Contact;
import com.africa.semicolon.data.models.User;
import com.africa.semicolon.dtos.request.CreateContactRequest;
import com.africa.semicolon.dtos.request.UpdateContactRequest;
import com.africa.semicolon.dtos.response.CreateContactResponse;
import com.africa.semicolon.dtos.response.UpdateContactResponse;

public class Mappers {
    public static Contact updateMapper(UpdateContactRequest newContact, Contact foundContact) {
        foundContact.setFirstName(newContact.getNewFirstName());
        foundContact.setLastName(newContact.getNewLastName());
        foundContact.setPhoneNumber(newContact.getNewPhoneNumber());

        return foundContact;
    }

    public static Contact  requestMapper(CreateContactRequest request) {
        Contact contact = new Contact();
        contact.setFirstName(request.getFirstName());
        contact.setLastName(request.getLastName());
        contact.setPhoneNumber(request.getPhoneNumber());
        return contact;
    }
    public static CreateContactResponse responseMapper(Contact contact) {
        CreateContactResponse response = new CreateContactResponse();
        response.setId(contact.getId());
        response.setFirstName(contact.getFirstName());
        response.setLastName(contact.getLastName());
        response.setPhoneNumber(contact.getPhoneNumber());
        response.setMessage("Contact Created Successfully");
        return response;
    }
    public static UpdateContactResponse responseUpdateMapper(UpdateContactResponse response, Contact updatedContact) {
        response.setFirstName(updatedContact.getFirstName());
        response.setLastName(updatedContact.getLastName());
        response.setPhoneNumber(updatedContact.getPhoneNumber());
        response.setMessage("Contact Updated Successfully");
        return response;
    }
}
