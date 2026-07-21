package com.techcrack.bookwise.controller;

import com.techcrack.bookwise.dtos.AuthorIdsRequest;
import com.techcrack.bookwise.dtos.AuthorRegisterDTO;
import com.techcrack.bookwise.dtos.AuthorResponseDTO;
import com.techcrack.bookwise.dtos.PendingAuthorDTO;
import com.techcrack.bookwise.entity.Author;
import com.techcrack.bookwise.entity.Subscription;
import com.techcrack.bookwise.service.AuthorRegistrationService;
import com.techcrack.bookwise.dtoMapper.SubscriptionHelper;
import com.techcrack.bookwise.responseHelper.ApiResponseEntity;
import com.techcrack.bookwise.dtoMapper.AuthorHelper;
import com.techcrack.bookwise.responseHelper.RegistrationResult;
import com.techcrack.bookwise.responseHelper.ResponseEntityHelper;
import com.techcrack.bookwise.service.AuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/author")
public class AuthorController {

    private final AuthorRegistrationService authorRegistrationService;
    private final AuthorHelper helper;
    private final SubscriptionHelper subscriptionHelper;
    private final Logger logger;
    private final AuthorService service;

    public AuthorController(AuthorRegistrationService authorRegistrationService, AuthorHelper helper ,SubscriptionHelper subscriptionHelper, AuthorService service) {
        this.authorRegistrationService = authorRegistrationService;
        this.service = service;
        this.helper = helper;
        this.subscriptionHelper = subscriptionHelper;
        this.logger = LoggerFactory.getLogger(AuthorController.class);
    }

    @PostMapping("/register")
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

    @GetMapping("/status/pending")
    public ResponseEntity<ApiResponseEntity<List<PendingAuthorDTO>>> getAuthorsForApproval() {
        logger.info("Author Approval Request Received");

        List<Author> authors = service.getPendingAuthors();

        List<PendingAuthorDTO> response = helper.mapToPendingAuthorDTOs(authors);

        logger.info("Author Pending Request Completed");
        return ResponseEntityHelper
                .buildSuccessResponse("Pending Authors Fetched Successfully", response);
    }

    @PostMapping("/approve")
    public ResponseEntity<ApiResponseEntity<String>> approveAuthors(@RequestBody AuthorIdsRequest request) {
        logger.info("Request Received to approve Authors");

        int authorsCount = service.approveAuthors(request.getAuthorIds());

        logger.info("Request Completed for approve Authors");

        return ResponseEntityHelper
                .buildSuccessResponse("Authors Approved Successfully", authorsCount + " author status is updated.");
    }
}
