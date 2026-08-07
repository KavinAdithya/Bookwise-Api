package com.techcrack.bookwise.controller;

import com.techcrack.bookwise.abstractions.AuthorService;
import com.techcrack.bookwise.dtos.author.request.AuthorIdsRequest;
import com.techcrack.bookwise.dtos.author.request.AuthorRegisterDTO;
import com.techcrack.bookwise.dtos.author.response.AuthorResponseDTO;
import com.techcrack.bookwise.dtos.author.request.PendingAuthorDTO;
import com.techcrack.bookwise.entity.Author;
import com.techcrack.bookwise.entity.Subscription;
import com.techcrack.bookwise.jwt.CurrentUserService;
import com.techcrack.bookwise.service.AuthorRegistrationService;
import com.techcrack.bookwise.helper.SubscriptionHelper;
import com.techcrack.bookwise.responseHelper.ApiResponseEntity;
import com.techcrack.bookwise.helper.AuthorHelper;
import com.techcrack.bookwise.responseHelper.RegistrationResult;
import com.techcrack.bookwise.responseHelper.ResponseEntityHelper;
import com.techcrack.bookwise.utils.AbstractController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/author")
public class AuthorController extends AbstractController<AuthorController, AuthorService, AuthorHelper> {

    private final AuthorRegistrationService authorRegistrationService;
    private final SubscriptionHelper subscriptionHelper;

    public AuthorController(AuthorRegistrationService authorRegistrationService, AuthorHelper helper ,SubscriptionHelper subscriptionHelper, AuthorService service, CurrentUserService userSession) {
        super(AuthorController.class, service, helper, userSession);
        this.authorRegistrationService = authorRegistrationService;
        this.subscriptionHelper = subscriptionHelper;
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

    @PostMapping("/reject")
    public ResponseEntity<ApiResponseEntity<String>> rejectAuthors(@RequestBody AuthorIdsRequest request) {
        logger.info("Request Received to reject Authors");

        int authorsCount = service.rejectAuthors(request.getAuthorIds());

        logger.info("Request Completed for reject Authors");

        return ResponseEntityHelper
                .buildSuccessResponse("Authors Rejected Successfully", authorsCount + " author status is updated.");
    }
}
