package com.techcrack.bookwise.controller;

import com.techcrack.bookwise.dtos.JwtAuthenticatedTokenResponseDTO;
import com.techcrack.bookwise.dtos.UserAuthenticateDTO;
import com.techcrack.bookwise.dtos.UserRegisterDTO;
import com.techcrack.bookwise.dtos.UserResponseDTO;
import com.techcrack.bookwise.entity.Subscription;
import com.techcrack.bookwise.entity.Users;
import com.techcrack.bookwise.service.SubscriptionService;
import com.techcrack.bookwise.service.UserService;
import com.techcrack.bookwise.utils.responseHelper.ApiResponseEntity;
import com.techcrack.bookwise.utils.dtoMapper.SubscriptionHelper;
import com.techcrack.bookwise.utils.dtoMapper.UserHelper;
import com.techcrack.bookwise.utils.responseHelper.ResponseEntityHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService service;
    private final UserHelper helper;
    private final Logger logger;
    private final SubscriptionService subscriptionService;
    private final SubscriptionHelper subscriptionHelper;

    public UserController(UserService service, UserHelper helper, SubscriptionService subscriptionService, SubscriptionHelper subscriptionHelper) {
        this.service = service;
        this.helper = helper;
        this.logger = LoggerFactory.getLogger(UserController.class);
        this.subscriptionService = subscriptionService;
        this.subscriptionHelper = subscriptionHelper;
    }

    @PostMapping("/users/register")
    public ResponseEntity<ApiResponseEntity<UserResponseDTO>> registerUser(@RequestBody UserRegisterDTO userRegisterDTO) {
        logger.info("Request received to create a user with {}", userRegisterDTO.getUsername());

        Users user = helper.mapToUser(userRegisterDTO);
        user = service.register(user);

        Subscription subscription = subscriptionHelper.mapToSubscription(userRegisterDTO.getSubscription());
        subscription.setUser(user);
        subscription = subscriptionService.register(subscription);

        logger.info("Request completed for create user {}", user.getUsername());
        UserResponseDTO response =  helper.mapToUserResponse(user,
                    subscriptionHelper.mapToSubscriptionResponse(subscription)
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