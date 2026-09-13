package com.techcrack.bookwise.controller;

import com.techcrack.bookwise.abstractions.AuthorService;
import com.techcrack.bookwise.constans.enums.Status;
import com.techcrack.bookwise.dtos.author.request.AuthorIdsRequest;
import com.techcrack.bookwise.dtos.author.request.AuthorRegisterRequest;
import com.techcrack.bookwise.dtos.author.response.AdminViewAuthorResponse;
import com.techcrack.bookwise.dtos.author.response.AuthorRegisterResponse;
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

    @GetMapping("/filter")
    public ResponseEntity<ApiResponseEntity<List<AdminViewAuthorResponse>>> getAuthorsForApproval(@RequestParam("status") Status status) {
        logger.info("Author Approval Request Received");

        List<AdminViewAuthorResponse> authors = service.getAuthorsBasedOnStatus(status);


        logger.info("Author Pending Request Completed");
        return ResponseEntityHelper
                .buildSuccessResponse("Pending Authors Fetched Successfully", authors);
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
    public ResponseEntity<ApiResponseEntity<List<AdminViewAuthorResponse>>> getAllAuthors() {
        logger.info("Request Received to fetch all authors");

        List<AdminViewAuthorResponse> authors = service.getAllActiveAuthorForAdminView();

        logger.info("Request Completed to fetch all authors");
        return ResponseEntityHelper
                .buildSuccessResponse("Authors Fetched Successfully", authors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseEntity<ViewAuthorResponse>> getAuthor(@PathVariable("id") long id) {
        logger.info("Request Received to fetch a author with {}" , id);

        Author author = service.getAuthorById(id);

        ViewAuthorResponse viewAuthorResponse = mapper.mapToViewAuthorResponse(author);

        logger.info("Request Completed to fetch a author with {}", id);

        return ResponseEntityHelper
                .buildSuccessResponse("Author Fetched Successfully", viewAuthorResponse);
    }

}
