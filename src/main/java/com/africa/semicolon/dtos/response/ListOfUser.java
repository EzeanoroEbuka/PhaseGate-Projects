package com.africa.semicolon.dtos.response;

import com.africa.semicolon.data.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ListOfUser {
    List<User> users;
}
