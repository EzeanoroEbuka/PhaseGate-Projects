package com.africa.semicolon.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignUpResponse {
    private String message;
    private String firstName;
    private boolean isLoggedIn;

}
