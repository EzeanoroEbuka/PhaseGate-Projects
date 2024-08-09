package com.africa.semicolon.services;

import com.africa.semicolon.ExceptionHandling;
import com.africa.semicolon.data.models.Contact;
import com.africa.semicolon.data.repositories.ContactRepository;
import com.africa.semicolon.dtos.request.CreateContactRequest;
import com.africa.semicolon.dtos.request.UpdateContactRequest;
import com.africa.semicolon.dtos.response.CreateContactResponse;
import com.africa.semicolon.dtos.response.DeleteResponse;
import com.africa.semicolon.dtos.response.UpdateContactResponse;
import com.africa.semicolon.utilities.Mappers;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactRepository repository;
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
        return repository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public UpdateContactResponse updateContact(UpdateContactRequest update , String phoneNumber) {

        UpdateContactResponse response = new UpdateContactResponse();

        Contact foundContact = repository.findByPhoneNumber(update.getPhoneNumber());
        if(foundContact != null) {
            response.setMessage("Contact Already Exists");
            return response;
           // throw new ExceptionHandling("Contact Already Exists");
        }
        Contact foundContactTwo = repository.findByPhoneNumber(phoneNumber);
        if(foundContactTwo != null) {
            Contact updatedDetails = Mappers.updateMapper(update, foundContactTwo);
            updatedDetails = repository.save(updatedDetails);
            return Mappers.responseUpdateMapper(response,updatedDetails);
        }
        else  {
            throw new IllegalArgumentException("Contact Not Found");
        }
    }
    @Override
    public DeleteResponse deleteContact(String phoneNumber) {
        DeleteResponse response = new DeleteResponse();
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
}


//    public Either<String, Integer> divide(int x, int y) {
//        if (y == 0) {
//            return Either.left("Cannot divide by zero");
//        } else {
//            return Either.right(x / y);
//        }
//    }