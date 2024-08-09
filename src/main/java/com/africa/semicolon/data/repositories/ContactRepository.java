package com.africa.semicolon.data.repositories;

import com.africa.semicolon.data.models.Contact;
import jakarta.annotation.Resource;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface ContactRepository extends MongoRepository<Contact, String> {
    Contact findByPhoneNumber(String phoneNumber);
    Contact findContactById(String id);
}
