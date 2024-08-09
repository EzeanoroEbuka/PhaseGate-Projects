package com.africa.semicolon.data.repositories;

import com.africa.semicolon.data.models.Contact;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class  ContactTest {
    @Autowired
    private ContactRepository contactRepository;

    @Test
    public void testAddContact() {
        Contact contact = new Contact();
        contact.setFirstName("John");
        contact.setLastName("Smith");
        contactRepository.save(contact);
        assertEquals(1, contactRepository.count());
    }

}
