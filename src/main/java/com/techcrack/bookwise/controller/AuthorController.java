package com.techcrack.bookwise.controller;

import com.techcrack.bookwise.abstractions.AuthorService;
import com.techcrack.bookwise.dtos.author.request.AuthorIdsRequest;
import com.techcrack.bookwise.dtos.author.request.AuthorRegisterRequest;
import com.techcrack.bookwise.dtos.author.response.AuthorRegisterResponse;
import com.techcrack.bookwise.dtos.author.response.PendingAuthorResponse;
import com.techcrack.bookwise.dtos.author.response.ViewAuthorResponse;
import com.techcrack.bookwise.entity.Author;
import com.techcrack.bookwise.entity.Subscription;
import com.techcrack.bookwise.abstractions.CurrentUserService;
import com.techcrack.bookwise.service.AuthorRegistrationService; 
import com.techcrack.bookwise.responseHelper.ApiResponseEntity;
import com.techcrack.bookwise.mapper.AuthorMapper;
import com.techcrack.bookwise.responseHelper.RegistrationResult;
import com.techcrack.bookwise.responseHelper.ResponseEntityHelper;
import com.techcrack.bookwise.utils.AbstractController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController extends AbstractController<AuthorController, AuthorService, AuthorMapper> {

    private final AuthorRegistrationService authorRegistrationService;

    public AuthorController(AuthorRegistrationService authorRegistrationService, AuthorMapper mapper , AuthorService service, CurrentUserService userSession) {
        super(AuthorController.class, service, mapper, userSession);
        this.authorRegistrationService = authorRegistrationService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponseEntity<AuthorRegisterResponse>> register(@RequestBody AuthorRegisterRequest authorRegisterRequest) {
        logger.info("Request received for author registration {}", authorRegisterRequest.getUser().getUsername());

        Author author = mapper.mapToAuthor(authorRegisterRequest);

        RegistrationResult<Author, Subscription> authorSubscriptionRegistrationResult = authorRegistrationService.register(author);

        AuthorRegisterResponse response = mapper.mapToAuthorRegisterResponse(authorSubscriptionRegistrationResult.entity(),
                                authorSubscriptionRegistrationResult.relatedEntity());

        logger.info("Request completed for author registration {}", response.getUser().getUsername());
        return ResponseEntityHelper
                .buildSuccessResponse("Author Registered successfully", response);
    }

    @GetMapping("/status/pending")
    public ResponseEntity<ApiResponseEntity<List<PendingAuthorResponse>>> getAuthorsForApproval() {
        logger.info("Author Approval Request Received");

        List<Author> authors = service.getPendingAuthors();

        List<PendingAuthorResponse> response = mapper.mapToPendingAuthorResponses(authors);

        logger.info("Author Pending Request Completed");
        return ResponseEntityHelper
                .buildSuccessResponse("Pending Authors Fetched Successfully", response);
    }

    @PatchMapping("/approve")
    public ResponseEntity<ApiResponseEntity<String>> approveAuthors(@RequestBody AuthorIdsRequest request) {
        logger.info("Request Received to approve Authors");

        int authorsCount = service.approveAuthors(request.getAuthorIds());

        logger.info("Request Completed for approve Authors");

        return ResponseEntityHelper
                .buildSuccessResponse("Authors Approved Successfully", authorsCount + " authors status were updated.");
    }

    @PatchMapping("/reject")
      public ResponseEntity<ApiResponseEntity<String>> rejectAuthors(@RequestBody AuthorIdsRequest request) {
        logger.info("Request Received to reject Authors");

        int authorsCount = service.rejectAuthors(request.getAuthorIds());

        logger.info("Request Completed for reject Authors");

        return ResponseEntityHelper
                .buildSuccessResponse("Authors Rejected Successfully", authorsCount + " authors status were updated.");
    }

    @GetMapping
    public ResponseEntity<ApiResponseEntity<List<ViewAuthorResponse>>> getAllAuthors() {
        logger.info("Request Received to fetch all authors");

        List<Author> authors = service.getAllActiveAuthors();
        List<ViewAuthorResponse> responses = mapper.mapToViewAuthorResponses(authors);

        logger.info("Request Completed to fetch all authors");
        return ResponseEntityHelper
                .buildSuccessResponse("Authors Fetched Successfully", responses);
    }

}
