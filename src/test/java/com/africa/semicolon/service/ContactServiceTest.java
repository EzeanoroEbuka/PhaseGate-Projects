package com.africa.semicolon.service;

import com.africa.semicolon.ExceptionHandling;
import com.africa.semicolon.dtos.request.CreateContactRequest;
import com.africa.semicolon.dtos.request.UpdateContactRequest;
import com.africa.semicolon.dtos.response.CreateContactResponse;
import com.africa.semicolon.dtos.response.DeleteResponse;
import com.africa.semicolon.dtos.response.UpdateContactResponse;
import com.africa.semicolon.services.ContactServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
public class ContactServiceTest {

    @Autowired
    private ContactServiceImpl service;

    @BeforeEach
    public void refreshDB() {
        service.deleteAllContact();
    }

    @Test
    public void ContactIsCreatedAndSavedTest() {
        CreateContactRequest request = new CreateContactRequest();
        request.setFirstName("John");
        request.setLastName("Smith");
        request.setPhoneNumber("555-555-5555");
        CreateContactResponse newContact = service.addContact(request);
        assertEquals(request.getPhoneNumber(), newContact.getPhoneNumber());
    }

    @Test
    public void NoDuplicateContactPhoneNumberCreatedTest() {
        CreateContactRequest request = new CreateContactRequest();
        request.setFirstName("John");
        request.setLastName("Smith");
        request.setPhoneNumber("555-555-5555");
        CreateContactRequest request2 = new CreateContactRequest();
        request2.setFirstName("James");
        request2.setLastName("Samuel");
        request2.setPhoneNumber("555-555-5555");
        CreateContactResponse newContact = service.addContact(request2);
        assertEquals(request.getPhoneNumber(), newContact.getPhoneNumber());
    }

    @Test
    public void ContactCanBeUpdatedTest() {
        CreateContactRequest contact = new CreateContactRequest();
        contact.setFirstName("Mark");
        contact.setLastName("Ethan");
        contact.setPhoneNumber("445-9809-988");
        service.addContact(contact);
        UpdateContactRequest anotherContact = new UpdateContactRequest();
        anotherContact.setFirstName("Adam");
        anotherContact.setLastName("Satan");
        anotherContact.setPhoneNumber("999-000-666");
        UpdateContactResponse updated = service.updateContact(anotherContact, "445-9809-988");
        assertEquals("999-000-666", updated.getPhoneNumber());

    }

    @Test
    public void ContactCanBeDeletedTest() {
        CreateContactRequest contact = new CreateContactRequest();
        contact.setFirstName("John");
        contact.setLastName("Smith");
        contact.setPhoneNumber("09012");
        service.addContact(contact);
        assertEquals(1, service.totalContacts());

        CreateContactRequest anotherContact = new CreateContactRequest();
        anotherContact.setFirstName("Mark");
        anotherContact.setLastName("Ethan");
        anotherContact.setPhoneNumber("445");
        service.addContact(anotherContact);
        assertEquals(2, service.totalContacts());
        DeleteResponse response = service.deleteContact("09012");
        assertEquals("Contact Deleted Successfully", response.getMessage());
        assertEquals(1, service.totalContacts());
    }

    @Test
    public void testThatAllContactCanBeDeleted() {
        CreateContactRequest request = new CreateContactRequest();
        request.setFirstName("John");
        request.setLastName("Smith");
        request.setPhoneNumber("555-555-5555");
        CreateContactResponse newContact = service.addContact(request);
        assertEquals(request.getPhoneNumber(), newContact.getPhoneNumber());
        CreateContactRequest anotherContact = new CreateContactRequest();
        anotherContact.setFirstName("Adam");
        anotherContact.setLastName("Satan");
        anotherContact.setPhoneNumber("999-000-666");
        service.addContact(anotherContact);
        service.deleteAllContact();
        assertEquals(0, service.totalContacts());
    }

    @Test
    public void testThatAllContactCanBeRetrievedByPhoneNumber() {
        CreateContactRequest contact = new CreateContactRequest();
        contact.setFirstName("John");
        contact.setLastName("Smith");
        contact.setPhoneNumber("555-555-5555");
        CreateContactResponse newContact = service.addContact(contact);
        CreateContactRequest anotherContact = new CreateContactRequest();
        anotherContact.setFirstName("Adam");
        anotherContact.setLastName("Ethan");
        anotherContact.setPhoneNumber("999-000-666");
        service.addContact(anotherContact);
        assertEquals(newContact.getPhoneNumber(), service.getContact(newContact.getPhoneNumber()).getPhoneNumber());
    }

    @Test
    public void ContactCannotBeUpdatedWithAnAlreadyExistingPoneNumberTest() {
        CreateContactRequest contact = new CreateContactRequest();
        contact.setFirstName("Mark");
        contact.setLastName("Ethan");
        contact.setPhoneNumber("445-9809-988");
        service.addContact(contact);
        assertEquals(1, service.totalContacts());
        UpdateContactRequest anotherContact = new UpdateContactRequest();
        anotherContact.setFirstName("Adam");
        anotherContact.setLastName("Satan");
        anotherContact.setPhoneNumber("445-9809-988");
        UpdateContactResponse updated = service.updateContact(anotherContact, " 45-9809-988");
        assertEquals("Contact Already Exists", updated.getMessage());

//        Throwable exception = assertThrows(ExceptionHandling.class, () -> {
//            throw new ExceptionHandling("Contact Already Exists");
//        });
//        assertEquals("Contact Already Exists", exception.getMessage());
//}
    }


}
