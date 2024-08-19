package com.africa.semicolon.services;

import com.africa.semicolon.data.models.Contact;
import com.africa.semicolon.data.models.User;
import com.africa.semicolon.dtos.request.*;
import com.africa.semicolon.dtos.response.*;

public interface UserService {
    LoginResponse login(LoginRequest request);
    LogOutResponse logOut(LogOutRequest request);
    SignUpResponse signUp(SignUpRequest request);
    CreateContactResponse createContact(CreateContactRequest request);
    DeleteContactResponse deleteAllContacts(DeleteRequest request);
    UpdateContactResponse updateContact(UpdateContactRequest request);
    DeleteContactResponse  deleteContact(DeleteRequest request);
    DeleteUserResponse deleteAllUser();
    ListOfUser getAllUsers();
    ListOfContacts getAllUserContacts(GetUserContacts contacts);
    User userFound(String username);
    Contact getUserContact(GetUserContacts request);
}
