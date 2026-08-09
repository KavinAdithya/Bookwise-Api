package com.techcrack.bookwise.controller;

import com.techcrack.bookwise.abstractions.UserService;
import com.techcrack.bookwise.constans.enums.Roles;
import com.techcrack.bookwise.dtos.jwt.JwtAuthenticatedTokenResponseDTO;
import com.techcrack.bookwise.dtos.user.request.UserAuthenticateDTO;
import com.techcrack.bookwise.dtos.user.request.UserRegisterRequest;
import com.techcrack.bookwise.dtos.user.response.UserRegisterResponse;
import com.techcrack.bookwise.entity.Subscription;
import com.techcrack.bookwise.entity.Users;
import com.techcrack.bookwise.abstractions.CurrentUserService;
import com.techcrack.bookwise.service.UserRegistrationService;
import com.techcrack.bookwise.responseHelper.ApiResponseEntity;
import com.techcrack.bookwise.mapper.SubscriptionMapper;
import com.techcrack.bookwise.mapper.UserHelper;
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
    private final SubscriptionMapper subscriptionMapper;

    public UserController(UserRegistrationService userRegistrationService, UserService service, UserHelper helper, SubscriptionMapper subscriptionMapper, CurrentUserService userSession) {
        super(UserController.class, service, helper, userSession);
        this.userRegistrationService = userRegistrationService;
        this.subscriptionMapper = subscriptionMapper;
    }

    @PostMapping("/users/register")
    public ResponseEntity<ApiResponseEntity<UserRegisterResponse>> registerUser(@RequestBody UserRegisterRequest userRegisterRequest) {
        logger.info("Request received to create a user with {}", userRegisterRequest.getUsername());

        Users user = mapper.mapToUser(userRegisterRequest, Roles.USER);
        Subscription subscription = subscriptionMapper.mapToSubscription(userRegisterRequest.getSubscription());

        RegistrationResult<Users, Subscription> registrationResult = userRegistrationService.register(user, subscription);

        logger.info("Request completed for create user {}", user.getUsername());

        UserRegisterResponse response =  mapper.mapToUserResponse(registrationResult.entity(),
                    subscriptionMapper.mapToSubscriptionResponse(registrationResult.relatedEntity())
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