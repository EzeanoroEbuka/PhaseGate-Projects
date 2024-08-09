package com.africa.semicolon.web;


import com.africa.semicolon.data.models.Contact;
import com.africa.semicolon.dtos.request.CreateContactRequest;
import com.africa.semicolon.dtos.request.UpdateContactRequest;
import com.africa.semicolon.dtos.response.CreateContactResponse;
import com.africa.semicolon.dtos.response.GetController;
import com.africa.semicolon.dtos.response.UpdateContactResponse;
import com.africa.semicolon.services.ContactService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping()
public class ContactController {

    private final ContactService contactService;

    @PostMapping("/post")
    public ResponseEntity<?> createContact(@RequestBody CreateContactRequest contactRequest) {

        try {
            CreateContactResponse response = contactService.addContact(contactRequest);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/{phoneNumber}")
    public ResponseEntity<?> getContact(@PathVariable ("phoneNumber") String phoneNumber) {

        try {
            Contact contact = contactService.getContact(phoneNumber);
          return new ResponseEntity<>(new GetController(contact.getFirstName(),contact.getLastName(),contact.getPhoneNumber()),HttpStatus.FOUND);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{phoneNumber}")
    public ResponseEntity<?> deleteContact(@PathVariable("phoneNumber") String phoneNumber) {
        try{
            return new ResponseEntity<>(contactService.deleteContact(phoneNumber), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/update/{phoneNumber}")
    public ResponseEntity<?> updateContact(@RequestBody UpdateContactRequest contactRequest, @PathVariable("phoneNumber") String phoneNumber) {

        try{
            UpdateContactResponse response = contactService.updateContact(contactRequest, phoneNumber);
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
