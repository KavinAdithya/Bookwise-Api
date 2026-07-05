package com.techcrack.bookwise.controller;

import com.techcrack.bookwise.dtos.JwtAuthenticatedTokenResponseDTO;
import com.techcrack.bookwise.dtos.UserAuthenticateDTO;
import com.techcrack.bookwise.dtos.UserRegisterDTO;
import com.techcrack.bookwise.dtos.UserResponseDTO;
import com.techcrack.bookwise.entity.Subscription;
import com.techcrack.bookwise.entity.Users;
import com.techcrack.bookwise.service.SubscriptionService;
import com.techcrack.bookwise.service.UserService;
import com.techcrack.bookwise.utils.SubscriptionHelper;
import com.techcrack.bookwise.utils.UserHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
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

    @PostMapping("/register")
    public UserResponseDTO registerUser(@RequestBody UserRegisterDTO userRegisterDTO) {
        logger.info("Request received to create a user with {}", userRegisterDTO.getUsername());

        Users user = helper.mapToUser(userRegisterDTO);
        user = service.register(user);

        Subscription subscription = subscriptionHelper.mapToSubscription(userRegisterDTO.getSubscription());
        subscription.setUser(user);
        subscription = subscriptionService.register(subscription);

        logger.info("Request completed for create user {}", user.getUsername());
        return helper.mapToUserResponse(user,
                    subscriptionHelper.mapToSubscriptionResponse(subscription)
                );
    }

    @PostMapping("/login")
    public JwtAuthenticatedTokenResponseDTO authenticateUser(@RequestBody UserAuthenticateDTO userAuthenticateDTO) {
        logger.info("Login Request received for {}", userAuthenticateDTO.getUsername());

        String token = service.authenticate(userAuthenticateDTO.getUsername(), userAuthenticateDTO.getPassword());

        logger.info("Login Request completed for {} Token generated : {}", userAuthenticateDTO.getPassword(), token);
        return new JwtAuthenticatedTokenResponseDTO(token);
    }
}