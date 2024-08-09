package com.africa.semicolon.services;

import com.africa.semicolon.dtos.request.*;
import com.africa.semicolon.dtos.response.*;

public interface UserService {
    LoginResponse login(LoginRequest request);
    SignUpResponse signUp(SignUpRequest request);
    CreateContactResponse createContact(CreateContactRequest request);
    DeleteResponse delete(DeleteRequest request);
    UpdateContactResponse updateContact(UpdateContactRequest request);
}
