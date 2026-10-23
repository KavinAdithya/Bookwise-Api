package com.techcrack.bookwise.service;


import com.techcrack.bookwise.constans.enums.Roles;
import com.techcrack.bookwise.dtos.user.context.AuthenticationResult;
import com.techcrack.bookwise.entity.UserPrincipal;
import com.techcrack.bookwise.entity.Users;
import com.techcrack.bookwise.exceptions.customized.InvalidDataException;
import com.techcrack.bookwise.exceptions.templates.Errors;
import com.techcrack.bookwise.service.jwt.JwtService;
import com.techcrack.bookwise.repository.UserRepository;
import com.techcrack.bookwise.service.notification.OtpService;
import com.techcrack.bookwise.service.user.PasswordResetService;
import com.techcrack.bookwise.service.user.UserServiceImpl;
import com.techcrack.bookwise.validations.UserServiceValidation;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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

            // Assert
            assertEquals(createUserRequest, createdUser);
        }

        @Test
        public void userCreationFailedWhenUserHasValidationErrors() {
            Errors errors = new Errors();

            errors.addErrorMessage("User Validation failed");

            when(userServiceValidation.validateUserData(any(Users.class)))
                    .thenReturn(errors);

            assertThrows(InvalidDataException.class, () -> userService.register(createUserRequest));
        }
    }

    @Nested
    @DisplayName("User Authentication test")
    class UserAuthenticationTest {
        private static String username;
        private static String password;

        @BeforeAll
        public static void setUp() {
            username = "User123";
            password = "User@123";
        }

        @Test
        void authenticateSuccessfully() {
            String username = "User-123";
            String password = "User@123";
            String token = "mock-jwt-token";

            UserPrincipal principal = mock(UserPrincipal.class);

            Authentication authentication = mock(Authentication.class);

            when(authManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                    .thenReturn(authentication);

            when(authentication.getPrincipal())
                    .thenReturn(principal);

            when(jwtService.generateToken(username))
                    .thenReturn(token);

            AuthenticationResult result =
                    userService.authenticate(username, password);

            assertNotNull(result);
            assertEquals(token, result.token());

            verify(authManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
            verify(jwtService).generateToken(username);
        }

        @Test
        void authenticationFailedForInvalidCredentials() {
            final String username = "user-123";
            final String password = "Wrong-Password@123";

            when(authManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                    .thenThrow(new BadCredentialsException("Invalid Credentials"));

            assertThrows(BadCredentialsException.class, () -> userService.authenticate(username, password));

            verify(jwtService, never()).generateToken(any(String.class));
        }

        @Test
        @DisplayName("User Credential Null cases")
        void authenticationFailedForNullCredentials() {

            when(authManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                    .thenThrow(new BadCredentialsException("Invalid Credentials"));

            assertThrows(BadCredentialsException.class, () -> userService.authenticate(null, null));

            verify(jwtService, never()).generateToken(any(String.class));
        }
    }

    @Test
    void getUserTest() {

    }
}