package com.africa.semicolon.services;

import com.africa.semicolon.ExceptionHandling;
import com.africa.semicolon.data.models.Contact;
import com.africa.semicolon.data.models.User;
import com.africa.semicolon.data.repositories.UserRepository;
import com.africa.semicolon.dtos.request.*;
import com.africa.semicolon.dtos.response.*;
import com.africa.semicolon.utilities.MapperForUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ContactService contactService;

    @Override
    public SignUpResponse signUp(SignUpRequest request) {
        if(!userExists(request.getEmail())){
            User user = MapperForUsers.signUpUser(request);
            userRepository.save(user);
            return MapperForUsers.responseForSignUp(user);
        }
        throw new ExceptionHandling("You Already Have An Account With this Email");
    }

    @Override
    public CreateContactResponse createContact(CreateContactRequest request) {
        Contact contact = new Contact();
        if(userExists(request.getUserEmail())){
            User foundUser = userFound(request.getUserEmail());
            CreateContactResponse response  = contactService.addContact(request);
            contact.setFirstName(response.getFirstName());
            contact.setLastName(response.getLastName());
            contact.setPhoneNumber(response.getPhoneNumber());
            contact.setId(response.getId());
            foundUser.getContacts().add(contact);
            userRepository.save(foundUser);
            return response;
        }
        throw new ExceptionHandling("You Have No Account For this Email...pls sign in");
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        LoginResponse loginResponse = new LoginResponse();

        if(userExists(request.getEmail())){
            User user = userFound(request.getEmail());
            if (user.getPassword().equals(request.getPassword())) {
                user.setLoggedIn(true);
                userRepository.save(user);
                loginResponse.setMessage("Successfully LoggedIn");
                return loginResponse;
            }
            loginResponse.setMessage("Invalid Password");
            return loginResponse;
        }
        loginResponse.setMessage("Invalid Email");
        return loginResponse;
    }

    @Override
    public LogOutResponse logOut(LogOutRequest request) {
        LogOutResponse response = new LogOutResponse();

        if(userExists(request.getEmail())){
            User user = userFound(request.getEmail());
            user.setLoggedIn(false);
            userRepository.save(user);
            response.setMessage("Successfully Logged Out");
            return response;
        }
        response.setMessage("You are not Yet Signed In or the Email Does Not Exist");
        return response;
    }

    @Override
    public DeleteContactResponse deleteAllContacts(DeleteRequest request) {
        DeleteContactResponse response = new DeleteContactResponse();
        User user = userFound(request.getUserEmail());
        user.getContacts().clear();
        userRepository.save(user);
        response.setMessage("You Have Successfully Deleted All Contacts");
        return response;
    }

    @Override
    public UpdateContactResponse updateContact(UpdateContactRequest request,OldDetailRequestUpdate secondRequest) {
        User user = userFound(secondRequest.getUserEmail());
        return contactService.updateContact(request,secondRequest);

    }

    @Override
    public DeleteContactResponse deleteContact(DeleteRequest request) {
        DeleteContactResponse response = new DeleteContactResponse();
        User user = userFound(request.getUserEmail());
        GetUserContacts retrieveUserContacts = new GetUserContacts();
        retrieveUserContacts.setContactNumber(request.getPhoneNumber());
        Contact foundContact = getUserContact(retrieveUserContacts);
        user.getContacts().remove(foundContact);
        userRepository.save(user);
        response.setMessage("You Have Successfully Deleted\n" + foundContact);
        return response;
    }

    @Override
    public DeleteUserResponse deleteAllUser() {
        DeleteUserResponse response = new DeleteUserResponse();
        userRepository.deleteAll();
        response.setMessage("All Users Deleted Successfully");
        return response;
    }

    @Override
    public ListOfUser getAllUsers() {
        ListOfUser users = new ListOfUser();
        users.setUsers(userRepository.findAll());
        return users;
    }

    @Override
    public ListOfContacts getAllUserContacts(GetUserContacts contacts) {
        ListOfContacts userContacts = new ListOfContacts();
        User user = userFound(contacts.getUserEmail());
        userContacts.setContacts(user.getContacts());
        return userContacts;
    }

    @Override
    public User userFound(String email) {
         return userRepository.findByEmail(email)
                 .orElseThrow(() -> new ExceptionHandling("There is No Account For this Email"));
    }

    private boolean userExists(String email){
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public Contact getUserContact(GetUserContacts request){
       User  foundUser = userFound(request.getUserEmail());
       for(Contact contact : foundUser.getContacts()){
           if(contact.getPhoneNumber().equals(request.getContactNumber())){
               return contact;
           }
       }
    return null;

    }
}
