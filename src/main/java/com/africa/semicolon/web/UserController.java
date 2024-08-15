package com.africa.semicolon.web;

import com.africa.semicolon.dtos.request.*;
import com.africa.semicolon.dtos.response.*;
import com.africa.semicolon.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/")
public class UserController {

        private final UserService userService;

        @PostMapping("user/signup")
        public ResponseEntity<?> signup(@RequestBody SignUpRequest request) {
            try{
                SignUpResponse response = userService.signUp(request);
                return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
            }
            catch(Exception e){
                return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
            }
        }
        @PatchMapping("user/logout")
        public ResponseEntity<?> logout(@RequestBody LogOutRequest request){
            try{
                LogOutResponse response = userService.logOut(request);
                return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
            }
            catch(Exception e){
                return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
            }
        }
        @PatchMapping("user/login")
        public ResponseEntity<?> login(@RequestBody LoginRequest request){
            try{
                LoginResponse response = userService.login(request);
                return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
            }
            catch(Exception e){
                return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
            }
        }

        @PostMapping("/user/create")
        public ResponseEntity<?> createContact(@RequestBody CreateContactRequest contactRequest) {

            try {
                CreateContactResponse response = userService.createContact(contactRequest);
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            }
            catch (Exception e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }

//        @GetMapping("/get/{phoneNumber}")
//        public ResponseEntity<?> getContact(@PathVariable ("phoneNumber") String phoneNumber) {
//
//            try {
//                Contact contact = userService.getContact(phoneNumber);
//                return new ResponseEntity<>(new GetController(contact.getFirstName(),contact.getLastName(),contact.getPhoneNumber()),HttpStatus.FOUND);
//            }
//            catch (Exception e) {
//                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//            }
//        }

        @DeleteMapping("/user/delete/")
        public ResponseEntity<?> deleteContact(@RequestBody DeleteRequest request) {
            try{
                return new ResponseEntity<>(userService.deleteContact(request), HttpStatus.OK);
            }
            catch (Exception e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }

        @PatchMapping("/user/update")
        public ResponseEntity<?> updateContact(@RequestBody UpdateContactRequest contactRequest, @RequestBody OldDetailRequestUpdate request) {

            try{
                UpdateContactResponse response = userService.updateContact(contactRequest, request);
                return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
            }
            catch (Exception e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }


}
