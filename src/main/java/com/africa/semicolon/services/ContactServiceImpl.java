package com.africa.semicolon.services;

import com.africa.semicolon.ExceptionHandling;
import com.africa.semicolon.data.models.Contact;
import com.africa.semicolon.data.repositories.ContactRepository;
import com.africa.semicolon.data.repositories.UserRepository;
import com.africa.semicolon.dtos.request.CreateContactRequest;
import com.africa.semicolon.dtos.request.OldDetailRequestUpdate;
import com.africa.semicolon.dtos.request.UpdateContactRequest;
import com.africa.semicolon.dtos.response.CreateContactResponse;
import com.africa.semicolon.dtos.response.DeleteContactResponse;
import com.africa.semicolon.dtos.response.UpdateContactResponse;
import com.africa.semicolon.utilities.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private ContactRepository repository;

    @Override
    public CreateContactResponse addContact(CreateContactRequest request) {
        if(ContactNumberIsNotPresent(request.getPhoneNumber())) {
            Contact contact = Mappers.requestMapper(request);
            repository.save(contact);
            return Mappers.responseMapper(contact);
        }
    throw new IllegalArgumentException("Contact Phone number Already Exist");
    }

    private boolean ContactNumberIsNotPresent(String phoneNumber) {
        Contact foundContact = validateContactNumber(phoneNumber);
        return foundContact == null;
    }

    private Contact validateContactNumber(String phoneNumber) {
        return contactExists(phoneNumber);
    }

    @Override
    public UpdateContactResponse updateContact(UpdateContactRequest update, OldDetailRequestUpdate secondRequest) {

        UpdateContactResponse response = new UpdateContactResponse();

        Contact foundContact = contactExists(update.getPhoneNumber());
        if(foundContact != null) {
            throw new ExceptionHandling("Contact Already Exists");
        }
        Contact foundContactTwo = contactExists(secondRequest.getPhoneNumber());
        if(foundContactTwo == null) {
            throw new ExceptionHandling("Contact Not Found");
        }
        Contact updatedDetails = Mappers.updateMapper(update, foundContactTwo);
        updatedDetails = repository.save(updatedDetails);
        return Mappers.responseUpdateMapper(response,updatedDetails);

    }
    @Override
    public DeleteContactResponse deleteContact(String phoneNumber) {
        DeleteContactResponse response = new DeleteContactResponse();
        Contact foundContact = validateContactNumber(phoneNumber);

        if(foundContact != null){
            repository.delete(foundContact);
            response.setMessage("Contact Deleted Successfully");
            return response;
        }
        response.setMessage("Contact Phone Number Not Found");
        return response;
    }

    @Override
    public void deleteAllContact() {
        repository.deleteAll();

    }

    @Override
    public Contact getContact(String phoneNumber) {

        if(ContactNumberIsNotPresent(phoneNumber)){
            throw new IllegalArgumentException("Contact Phone Number Not Found");
        }
        return validateContactNumber(phoneNumber);
    }
    @Override
    public Long totalContacts() {
        return repository.count();
    }

    @Override
    public List<Contact> getAllContacts() {
        return repository.findAll();
    }
    public Contact contactExists(String email) {
        return repository.findByPhoneNumber(email);

    }
}
