package com.techcrack.bookwise.service;


import com.techcrack.bookwise.constans.enums.Roles;
import com.techcrack.bookwise.entity.Users;
import com.techcrack.bookwise.exceptions.templates.Errors;
import com.techcrack.bookwise.jwt.JwtService;
import com.techcrack.bookwise.repository.UserRepository;
import com.techcrack.bookwise.validations.UserServiceValidation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("User Service Test")
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder encoder;
    @Mock
    private AuthenticationManager authManager;
    @Mock
    private JwtService jwtService;
    @Mock
    private OtpService otpService;
    @Mock
    private PasswordResetService passwordResetService;
    @Mock
    private UserServiceValidation userServiceValidation;

    @InjectMocks
    private UserServiceImpl userService;

    private Users createUserRequest;
    private Users createdUser;

    @BeforeEach
    public void initializeTestObjects() {
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

    @Nested
    @DisplayName("User Creation Test")
    class UserCreateTest {

        @Test
        public void userCreationSuccessfully() {
            // Arrange : Mock Data
            when(userServiceValidation.validateUserData(any(Users.class)))
                    .thenReturn(new Errors());
            when(userRepository.save(any(Users.class)))
                    .thenAnswer(invocation -> invocation.getArgument(0));
            when(encoder.encode(any(CharSequence.class)))
                    .thenReturn("Password Encoded");

            // Act Test
            Users users = userService.register(createUserRequest);

            System.out.println(users.getPassword());
            System.out.println(createUserRequest.getPassword());

            // Assert
            assertEquals(createUserRequest, createdUser);
        }
    }

    @Test
    void getUserTest() {

    }
}