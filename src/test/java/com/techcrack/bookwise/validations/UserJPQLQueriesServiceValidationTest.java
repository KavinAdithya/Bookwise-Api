package com.techcrack.bookwise.validations;

import com.techcrack.bookwise.applicationServices.user.validation.UserServiceValidation;
import com.techcrack.bookwise.constans.enums.Roles;
import com.techcrack.bookwise.applicationServices.user.entity.Users;
import com.techcrack.bookwise.exceptions.templates.Errors;
import com.techcrack.bookwise.applicationServices.user.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserJPQLQueriesServiceValidationTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceValidation userServiceValidation;


    @Nested
    @DisplayName("User Data Validation test")
    class UserJPQLQueriesDataTest {

        private static Users createdUserWithInvalidData;
        private static Users createUserRequest;


        @BeforeEach
        public void setUp() {

            createUserRequest = new Users();

            createUserRequest.initialize(null);
            createUserRequest.setUsername("User-123");
            createUserRequest.setRole(Roles.USER);
            createUserRequest.setPassword("User@123");
            createUserRequest.setContact("7895241306");
            createUserRequest.setName("User1");
            createUserRequest.setEmail("user1@gmail.com");
            createUserRequest.setAddress("bellandur, Bangalore");

            createdUserWithInvalidData = new Users();
            createdUserWithInvalidData.initialize(null);
            createdUserWithInvalidData.setUsername("User-123");
            createdUserWithInvalidData.setRole(Roles.USER);
            createdUserWithInvalidData.setPassword("Password@123");
            createdUserWithInvalidData.setContact("7895241306");
            createdUserWithInvalidData.setName("User1");
            createdUserWithInvalidData.setEmail("user1@gmail.com");
            createdUserWithInvalidData.setAddress("bellandur, Bangalore");
        }

        @Test
        void validateUserData() {
            when(userRepository.findExistingUsers(any(String.class), any(String.class), any(String.class)))
                    .thenReturn(new ArrayList<>());

            Errors errors = userServiceValidation.validateUserData(createUserRequest);

            assertFalse(errors.hasErrors());
        }

        @Test
        void userInvalidPasswordTest() {
            when(userRepository.findExistingUsers(any(String.class), any(String.class), any(String.class)))
                    .thenReturn(new ArrayList<>());

            createdUserWithInvalidData.setPassword("dfghjkl!2343");
            Errors errors = userServiceValidation.validateUserData(createdUserWithInvalidData);

            assertTrue(errors.hasErrors());
            assertEquals(1, errors.errorsCount());
        }

        @Test
        void userInvalidContactNumberTest() {
            when(userRepository.findExistingUsers(any(String.class), any(String.class), any(String.class)))
                    .thenReturn(new ArrayList<>());

            createdUserWithInvalidData.setContact("234h44b4j4");
            Errors errors = userServiceValidation.validateUserData(createdUserWithInvalidData);

            System.out.println(errors.getData());
            assertTrue(errors.hasErrors());
            assertEquals(1, errors.errorsCount());
        }

        @Test
        void userInvalidContactAndPassword() {
            when(userRepository.findExistingUsers(any(String.class), any(String.class), any(String.class)))
                    .thenReturn(new ArrayList<>());

            createdUserWithInvalidData.setPassword("Kakakakaka");
            createdUserWithInvalidData.setContact("72h2b2yu2u2ui920");

            Errors errors = userServiceValidation.validateUserData(createdUserWithInvalidData);

            assertTrue(errors.hasErrors());
            assertEquals(2, errors.errorsCount());
        }

        @Test
        void isUserAlreadyExists() {
            when(userRepository.existsByContact(any(String.class)))
                    .thenReturn(true);
            when(userRepository.existsByEmail(any(String.class)))
                    .thenReturn(true);
            when(userRepository.existsByUsername(any(String.class)))
                    .thenReturn(true);

            Errors errors = new Errors();
            userServiceValidation.findFailedConstraint(createUserRequest, errors);

            assertTrue(errors.hasErrors());
            assertEquals(3, errors.errorsCount());
        }
    }

    @Nested
    @DisplayName("All Password Test cases")
    class PasswordTest {
        private String correctPassword;
        private String passwordWithoutAlpha;
        private String passwordWithoutSplChar;
        private String passwordWithoutNumber;
        private String passwordWithoutSmallAlpha;
        private String passwordWithoutCapAlpha;
        private String passwordMinLength;

        @BeforeEach
        void setUp() {
            correctPassword = "User@123";
            passwordWithoutAlpha = "1234567@7";
            passwordWithoutSplChar = "User12345";
            passwordMinLength = "12";
            passwordWithoutNumber = "User@Ram";
            passwordWithoutSmallAlpha = "USER@123";
            passwordWithoutCapAlpha = "user@123";
        }

        @Test
        void validPassword() {
            boolean res = userServiceValidation.isValidPassWord(correctPassword);
            assertTrue(res);
        }

        @Test
        void wrongPasswordCases() {
            assertFalse(userServiceValidation.isValidPassWord(passwordWithoutAlpha));
            assertFalse(userServiceValidation.isValidPassWord(passwordWithoutNumber));
            assertFalse(userServiceValidation.isValidPassWord(passwordWithoutCapAlpha));
            assertFalse(userServiceValidation.isValidPassWord(passwordWithoutSmallAlpha));
            assertFalse(userServiceValidation.isValidPassWord(passwordWithoutSplChar));
            assertFalse(userServiceValidation.isValidPassWord(passwordMinLength));
        }

        @Test
        void edgeCasesTest() {
            assertFalse(userServiceValidation.isValidPassWord(null));
        }
    }

    @Nested
    @DisplayName("Phone Number Validation test cases")
    class PhoneNumberTest {
        private static String validPhoneNumber;
        private static String phoneNumberWrongLength;
        private static String phoneNumberInvalidDigit;

        @BeforeAll
        public static void setUp() {
            validPhoneNumber = "1234567890";
            phoneNumberInvalidDigit = "qwe1234567";
            phoneNumberWrongLength = "1223456789000";
        }

        @Test
        public void testContactNumber() {
            assertTrue(userServiceValidation.isValidContact(validPhoneNumber));
            assertFalse(userServiceValidation.isValidContact(phoneNumberInvalidDigit));
            assertFalse(userServiceValidation.isValidContact(phoneNumberWrongLength));
        }
    }
}