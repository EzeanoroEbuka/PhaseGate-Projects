package com.africa.semicolon.data.repositories;

import com.africa.semicolon.data.models.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface ContactRepository extends MongoRepository<Contact, String> {
    Contact findByPhoneNumber(String phoneNumber);

}
