package com.techcrack.bookwise.controller;

import com.techcrack.bookwise.dtos.AuthorRegisterDTO;
import com.techcrack.bookwise.dtos.AuthorResponseDTO;
import com.techcrack.bookwise.entity.Author;
import com.techcrack.bookwise.entity.Subscription;
import com.techcrack.bookwise.service.AuthorRegistrationService;
import com.techcrack.bookwise.utils.dtoMapper.SubscriptionHelper;
import com.techcrack.bookwise.utils.responseHelper.ApiResponseEntity;
import com.techcrack.bookwise.utils.dtoMapper.AuthorHelper;
import com.techcrack.bookwise.utils.responseHelper.RegistrationResult;
import com.techcrack.bookwise.utils.responseHelper.ResponseEntityHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorController {

    private final AuthorRegistrationService authorRegistrationService;
    private final AuthorHelper helper;
    private final SubscriptionHelper subscriptionHelper;
    private final Logger logger;

    public AuthorController(AuthorRegistrationService authorRegistrationService, AuthorHelper helper ,SubscriptionHelper subscriptionHelper) {
        this.authorRegistrationService = authorRegistrationService;
        this.helper = helper;
        this.subscriptionHelper = subscriptionHelper;
        this.logger = LoggerFactory.getLogger(AuthorController.class);
    }

    @PostMapping("/author/register")
    public ResponseEntity<ApiResponseEntity<AuthorResponseDTO>> register(@RequestBody AuthorRegisterDTO authorRegisterDTO) {
        logger.info("Request received for author registration {}", authorRegisterDTO.getUser().getUsername());

        Author author = helper.mapToAuthor(authorRegisterDTO);
        Subscription subscription = subscriptionHelper.mapToSubscription(authorRegisterDTO.getUser().getSubscription());

        RegistrationResult<Author, Subscription> authorSubscriptionRegistrationResult = authorRegistrationService.register(author, subscription);

        AuthorResponseDTO response = helper.mapToAuthorResponseDTO(author,
                                subscriptionHelper.mapToSubscriptionResponse(authorSubscriptionRegistrationResult.relatedEntity()));

        logger.info("Request completed for author registration {}", response.getUser().getUsername());
        return ResponseEntityHelper
                .buildSuccessResponse("Author Registered successfully", response);
    }
}
