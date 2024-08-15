package com.africa.semicolon.utilities;

import com.africa.semicolon.data.models.User;
import com.africa.semicolon.dtos.request.SignUpRequest;
import com.africa.semicolon.dtos.response.SignUpResponse;

public class MapperForUsers {
    public static User signUpUser(SignUpRequest request) {
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setLoggedIn(true);
        return user;
    }

    public static SignUpResponse responseForSignUp(User user){
        SignUpResponse response = new SignUpResponse();
        response.setFirstName(user.getFirstName());
        response.setMessage("Successfully Signed Up");
        response.setLoggedIn(user.isLoggedIn());
        return response;
    }
}
