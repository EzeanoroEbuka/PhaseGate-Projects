package com.africa.semicolon.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DeleteRequest {
    private String phoneNumber;
    private String userEmail;

}
