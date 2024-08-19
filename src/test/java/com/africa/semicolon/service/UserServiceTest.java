package com.africa.semicolon.service;

import com.africa.semicolon.ExceptionHandling;
import com.africa.semicolon.dtos.request.*;
import com.africa.semicolon.dtos.response.*;
import com.africa.semicolon.services.ContactService;
import com.africa.semicolon.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private ContactService contactService;

    @BeforeEach
    void setUp() {
        contactService.deleteAllContact();
        userService.deleteAllUser();
    }

    @Test
    public void testThatNoUserHasYetBeenSignedIn() {
        ListOfUser users = userService.getAllUsers();
        assertTrue(users.getUsers().isEmpty());
    }

    @Test
    public void testThatUserIsSignedIn() {
        SignUpRequest request = new SignUpRequest();
        request.setFirstName("Adam");
        request.setLastName("Smith");
        request.setEmail("adam.smith@gmail.com");
        request.setPassword("password");
        SignUpResponse response = userService.signUp(request);
        assertEquals(request.getFirstName(), response.getFirstName());
        assertEquals("Successfully Signed Up", response.getMessage());
        assertEquals(1, userService.getAllUsers().getUsers().size());
    }

    @Test
    public void testThatUserCanBeLoggedOutAfterSignedIn() {
        SignUpRequest request = new SignUpRequest();
        request.setFirstName("Adam");
        request.setLastName("Smith");
        request.setEmail("adam.smith@gmail.com");
        request.setPassword("password");
        userService.signUp(request);
        LogOutRequest request1 = new LogOutRequest();
        request1.setEmail("adam.smith@gmail.com");
        LogOutResponse response1 = userService.logOut(request1);
        assertEquals("Successfully Logged Out", response1.getMessage());
    }

    @Test
    public void testThatUserCanBeLoggedInAfterLogOut() {
        SignUpRequest request = new SignUpRequest();
        request.setFirstName("Adam");
        request.setLastName("Smith");
        request.setEmail("adam.smith@gmail.com");
        request.setPassword("password");
        userService.signUp(request);
        LogOutRequest request1 = new LogOutRequest();
        request1.setEmail("adam.smith@gmail.com");
        userService.logOut(request1);
        LoginRequest request2 = new LoginRequest();
        request2.setEmail("adam.smith@gmail.com");
        request2.setPassword("password");
        LoginResponse response2 = userService.login(request2);
        assertEquals(response2.getMessage(), "Successfully LoggedIn");
    }

    @Test
    public void testThatUserCanAddToItsContact() {
        SignUpRequest request = new SignUpRequest();
        request.setFirstName("Adam");
        request.setLastName("Smith");
        request.setEmail("adam.smith@gmail.com");
        request.setPassword("password");
        userService.signUp(request);
        CreateContactRequest contactRequest = new CreateContactRequest();
        contactRequest.setFirstName("Matthew");
        contactRequest.setLastName("fisher");
        contactRequest.setPhoneNumber("07031475772");
        contactRequest.setUserEmail("adam.smith@gmail.com");
        CreateContactResponse newContact = userService.createContact(contactRequest);
        assertEquals(newContact.getPhoneNumber(), "07031475772");
        GetUserContacts details = new GetUserContacts();
        details.setUserEmail("adam.smith@gmail.com");
        assertEquals(1, userService.getAllUserContacts(details).getContacts().size());
    }

    @Test
    public void testThatUserCanDeleteAllContact() {
        SignUpRequest request = new SignUpRequest();
        request.setFirstName("Adam");
        request.setLastName("Smith");
        request.setEmail("adam.smith@gmail.com");
        request.setPassword("password");
        userService.signUp(request);
        CreateContactRequest contactRequest = new CreateContactRequest();
        contactRequest.setFirstName("Matthew");
        contactRequest.setLastName("fisher");
        contactRequest.setPhoneNumber("07031475772");
        contactRequest.setUserEmail("adam.smith@gmail.com");
        CreateContactResponse newContact = userService.createContact(contactRequest);
        DeleteRequest deleteRequest = new DeleteRequest();
        deleteRequest.setUserEmail("adam.smith@gmail.com");
        userService.deleteAllContacts(deleteRequest);
        GetUserContacts details = new GetUserContacts();
        details.setUserEmail("adam.smith@gmail.com");
        assertEquals(0, userService.getAllUserContacts(details).getContacts().size());
    }
//
//    @Test
//    public  void testThatAUserCanDeleteAContact() {
//        SignUpRequest request = new SignUpRequest();
//        request.setFirstName("Adam");
//        request.setLastName("Smith");
//        request.setEmail("adam.smith@gmail.com");
//        request.setPassword("password");
//        userService.signUp(request);
//        CreateContactRequest contactRequest = new CreateContactRequest();
//        contactRequest.setFirstName("Matthew");
//        contactRequest.setLastName("fisher");
//        contactRequest.setPhoneNumber("07031475772");
//        contactRequest.setUserEmail("adam.smith@gmail.com");
//        CreateContactResponse newContact = userService.createContact(contactRequest);
//        DeleteRequest deleteRequest = new DeleteRequest();
//        deleteRequest.setUserEmail("adam.smith@gmail.com");
//        userService.deleteContact(deleteRequest);
//        GetUserContacts details =  new GetUserContacts();
//        details.setUserEmail("adam.smith@gmail.com");
//        details.setContactNumber("07031475772");
//        assertNull(userService.getUserContact(details));
//    }

    @Test
    public void testThatUserCanUpdateContact() {
        SignUpRequest request = new SignUpRequest();
        request.setFirstName("Adam");
        request.setLastName("Smith");
        request.setEmail("adam.smith@gmail.com");
        request.setPassword("password");
        userService.signUp(request);
        CreateContactRequest contactRequest = new CreateContactRequest();
        contactRequest.setFirstName("Matthew");
        contactRequest.setLastName("fisher");
        contactRequest.setPhoneNumber("07031475772");
        contactRequest.setUserEmail("adam.smith@gmail.com");
        CreateContactResponse newContact = userService.createContact(contactRequest);
        UpdateContactRequest requestUpdate = new UpdateContactRequest();
        requestUpdate.setNewFirstName("Luke");
        requestUpdate.setNewLastName("fisher");
        requestUpdate.setNewPhoneNumber("09031475772");
        requestUpdate.setUserEmail("adam.smith@gmail.com");
        requestUpdate.setOldPhoneNumber("07031475772");
        UpdateContactResponse response = userService.updateContact(requestUpdate);
        assertEquals(response.getMessage(), "Contact Updated Successfully");
        assertEquals(response.getPhoneNumber(), "09031475772");

    }

    @Test
    public void testThatUserCannotCreateContactWhenTheyAreNotLoggedIn() {
        SignUpRequest request = new SignUpRequest();
        request.setFirstName("Adam");
        request.setLastName("Smith");
        request.setEmail("adam.smith@gmail.com");
        request.setPassword("password");
        userService.signUp(request);
        LogOutRequest request1 = new LogOutRequest();
        request1.setEmail("adam.smith@gmail.com");
        userService.logOut(request1);
        CreateContactRequest contactRequest = new CreateContactRequest();
        contactRequest.setFirstName("Matthew");
        contactRequest.setLastName("fisher");
        contactRequest.setPhoneNumber("07031475772");
        contactRequest.setUserEmail("adam.smith@gmail.com");
        assertThrows(ExceptionHandling.class, () -> userService.createContact(contactRequest));
    }
}
