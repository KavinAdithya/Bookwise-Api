package com.techcrack.bookwise.validations;

import com.techcrack.bookwise.constans.enums.Roles;
import com.techcrack.bookwise.entity.Users;
import com.techcrack.bookwise.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceValidationTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceValidation userServiceValidation;

    private Users createdUser;
    private Users createUserRequest;


    @BeforeEach
    void setUp() {
        createUserRequest = new Users();

        createUserRequest.initialize(null);
        createUserRequest.setUsername("User-123");
        createUserRequest.setRole(Roles.USER);
        createUserRequest.setPassword("User@123");
        createUserRequest.setContact("7895241306");
        createUserRequest.setName("User1");
        createUserRequest.setEmail("user1@gmail.com");
        createUserRequest.setAddress("bellandur, Bangalore");

        createdUser = new Users();
        createdUser.initialize(null);
        createdUser.setUsername("User-123");
        createdUser.setRole(Roles.USER);
        createdUser.setPassword("Password Encoded");
        createdUser.setContact("7895241306");
        createdUser.setName("User1");
        createdUser.setEmail("user1@gmail.com");
        createdUser.setAddress("bellandur, Bangalore");
    }

    @Test
    void validateUserData() {

    }

    @Test
    void isUserAlreadyExists() {
    }

    @Test
    void isValidPassWord() {
    }

    @Test
    void isValidContact() {
    }
}