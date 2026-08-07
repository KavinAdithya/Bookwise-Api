package com.techcrack.bookwise.controller;

import com.techcrack.bookwise.abstractions.UserService;
import com.techcrack.bookwise.constans.Roles;
import com.techcrack.bookwise.dtos.jwt.JwtAuthenticatedTokenResponseDTO;
import com.techcrack.bookwise.dtos.user.request.UserAuthenticateDTO;
import com.techcrack.bookwise.dtos.user.request.UserRegisterDTO;
import com.techcrack.bookwise.dtos.user.response.UserResponseDTO;
import com.techcrack.bookwise.entity.Subscription;
import com.techcrack.bookwise.entity.Users;
import com.techcrack.bookwise.jwt.CurrentUserService;
import com.techcrack.bookwise.service.UserRegistrationService;
import com.techcrack.bookwise.responseHelper.ApiResponseEntity;
import com.techcrack.bookwise.helper.SubscriptionHelper;
import com.techcrack.bookwise.helper.UserHelper;
import com.techcrack.bookwise.responseHelper.RegistrationResult;
import com.techcrack.bookwise.responseHelper.ResponseEntityHelper;
import com.techcrack.bookwise.utils.AbstractController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController extends AbstractController<UserController, UserService, UserHelper> {
    private final UserRegistrationService userRegistrationService;
    private final SubscriptionHelper subscriptionHelper;

    public UserController(UserRegistrationService userRegistrationService, UserService service, UserHelper helper, SubscriptionHelper subscriptionHelper, CurrentUserService userSession) {
        super(UserController.class, service, helper, userSession);
        this.userRegistrationService = userRegistrationService;
        this.subscriptionHelper = subscriptionHelper;
    }

    @PostMapping("/users/register")
    public ResponseEntity<ApiResponseEntity<UserResponseDTO>> registerUser(@RequestBody UserRegisterDTO userRegisterDTO) {
        logger.info("Request received to create a user with {}", userRegisterDTO.getUsername());

        Users user = helper.mapToUser(userRegisterDTO, Roles.USER);
        Subscription subscription = subscriptionHelper.mapToSubscription(userRegisterDTO.getSubscription());

        RegistrationResult<Users, Subscription> registrationResult = userRegistrationService.register(user, subscription);

        logger.info("Request completed for create user {}", user.getUsername());

        UserResponseDTO response =  helper.mapToUserResponse(registrationResult.entity(),
                    subscriptionHelper.mapToSubscriptionResponse(registrationResult.relatedEntity())
                );

        return ResponseEntityHelper.buildSuccessResponse(
                "User created successfully",
                response
        );
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponseEntity<JwtAuthenticatedTokenResponseDTO>> authenticateUser(@RequestBody UserAuthenticateDTO userAuthenticateDTO) {
        logger.info("Login Request received for {}", userAuthenticateDTO.getUsername());

        String token = service.authenticate(userAuthenticateDTO.getUsername(), userAuthenticateDTO.getPassword());

        logger.info("Login Request completed for {} Token generated : {}", userAuthenticateDTO.getPassword(), token);
        JwtAuthenticatedTokenResponseDTO response =  new JwtAuthenticatedTokenResponseDTO(token);

        return ResponseEntityHelper.buildSuccessResponse(
            "Authentication success! Token Generated",
                    response
        );
    }
}