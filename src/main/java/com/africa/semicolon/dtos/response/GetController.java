package com.africa.semicolon.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetController {
    private String firstName;
    private String lastName;
    private String phoneNumber;
}
