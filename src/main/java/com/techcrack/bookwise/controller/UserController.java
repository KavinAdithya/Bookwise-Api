package com.techcrack.bookwise.controller;

import com.techcrack.bookwise.abstractions.UserService;
import com.techcrack.bookwise.dtos.user.response.JwtTokenResponse;
import com.techcrack.bookwise.dtos.user.request.UserAuthenticateRequest;
import com.techcrack.bookwise.dtos.user.request.UserRegisterRequest;
import com.techcrack.bookwise.dtos.user.response.UserRegisterResponse;
import com.techcrack.bookwise.entity.Subscription;
import com.techcrack.bookwise.entity.Users;
import com.techcrack.bookwise.abstractions.CurrentUserService;
import com.techcrack.bookwise.service.UserRegistrationService;
import com.techcrack.bookwise.responseHelper.ApiResponseEntity;
import com.techcrack.bookwise.mapper.UserMapper;
import com.techcrack.bookwise.responseHelper.RegistrationResult;
import com.techcrack.bookwise.responseHelper.ResponseEntityHelper;
import com.techcrack.bookwise.utils.AbstractController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController extends AbstractController<UserController, UserService, UserMapper> {
    private final UserRegistrationService userRegistrationService;

    public UserController(UserRegistrationService userRegistrationService, UserService service, UserMapper helper, CurrentUserService userSession) {
        super(UserController.class, service, helper, userSession);
        this.userRegistrationService = userRegistrationService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponseEntity<UserRegisterResponse>> registerUser(@RequestBody UserRegisterRequest userRegisterRequest) {
        logger.info("Request received to create a user with {}", userRegisterRequest.getUsername());

        Users user = mapper.mapToUser(userRegisterRequest);

        RegistrationResult<Users, Subscription> registrationResult = userRegistrationService.register(user, userRegisterRequest.getSubscription().getPlan());

        logger.info("Request completed for create user {}", user.getUsername());

        UserRegisterResponse response =  mapper.mapToUserResponse(
                    registrationResult.entity(),
                    registrationResult.relatedEntity()
                );

        return ResponseEntityHelper.buildSuccessResponse(
                "User created successfully",
                response
        );
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponseEntity<JwtTokenResponse>> authenticateUser(@RequestBody UserAuthenticateRequest userAuthenticateRequest) {
        logger.info("Login Request received for {}", userAuthenticateRequest.getUsername());

        String token = service.authenticate(userAuthenticateRequest.getUsername(), userAuthenticateRequest.getPassword());

        logger.info("Login Request completed for {} Token generated : {}", userAuthenticateRequest.getPassword(), token);
        JwtTokenResponse response =  mapper.mapToJwtToken(token);

        return ResponseEntityHelper.buildSuccessResponse(
            "Authentication success! Token Generated",
                    response
        );
    }
}