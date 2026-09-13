package com.techcrack.bookwise.controller;

import com.techcrack.bookwise.abstractions.BookService;
import com.techcrack.bookwise.dtos.book.response.AuthorBookViewResponse;
import com.techcrack.bookwise.dtos.book.response.ViewBookResponse;
import com.techcrack.bookwise.mapper.BookMapper;
import com.techcrack.bookwise.dtos.book.request.BookIdsRequest;
import com.techcrack.bookwise.dtos.book.request.BookRegisterRequest;
import com.techcrack.bookwise.dtos.book.response.BookRegisterResponse;
import com.techcrack.bookwise.dtos.book.response.PendingBookResponse;
import com.techcrack.bookwise.entity.Book;
import com.techcrack.bookwise.abstractions.CurrentUserService;
import com.techcrack.bookwise.responseHelper.ApiResponseEntity;
import com.techcrack.bookwise.responseHelper.ResponseEntityHelper;
import com.techcrack.bookwise.utils.AbstractController;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController extends AbstractController<BookController, BookService, BookMapper> {

    public BookController(BookService service, BookMapper helper, CurrentUserService userSession) {
        super(BookController.class, service, helper, userSession);
    }

    @PostMapping(
            value = "/register",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponseEntity<BookRegisterResponse>> register(
                    @RequestPart("book") BookRegisterRequest request,
                    @RequestPart("coverImage") MultipartFile coverImage)  {
        logger.info("Request Received for register a new book with {}", request.getTitle());

        Book book = service.createBook(request, coverImage);

        BookRegisterResponse responseDTO = mapper.mapToBookRegisterResponse(book);

        logger.info("Request Completed for register a new book with {}", request.getTitle());

        return ResponseEntityHelper
                .buildSuccessResponse("Book Registered Successfully", responseDTO);
    }

    @GetMapping
    public ResponseEntity<ApiResponseEntity<List<ViewBookResponse>>> getAllBooksForView() {
        logger.info("Get all books request received");

        List<Book> books = service.getAllApprovedAndAvailableBooks();

        List<ViewBookResponse> response = mapper.mapToViewBookResponses(books);

        logger.info("Get All Books request completed");
        return ResponseEntityHelper
                .buildSuccessResponse(
                        "Books Fetched Successfully",
                        response
                );
    }

    @GetMapping("/status/pending")
    public ResponseEntity<ApiResponseEntity<List<PendingBookResponse>>> getAllPendingBooks() {
        logger.info("Request Received to get all pending books");

        List<Book> books = service.getAllPendingBooks();

        List<PendingBookResponse> response = mapper.mapToPendingBookResponses(books);

        logger.info("Request Completed to get all pending books");

        return ResponseEntityHelper
                .buildSuccessResponse(
                        "Pending Books Fetched Successfully",
                        response
                );
    }

    @PatchMapping("/approve")
    public ResponseEntity<ApiResponseEntity<String>> approveAllBooks(@RequestBody BookIdsRequest request) {
        logger.info("Request Received to approve books {}", request.getBookIds());

        int booksCount = service.approveAllBooks(request.getBookIds());

        logger.info("Request Completed for approve books");

        return ResponseEntityHelper
                .buildSuccessResponse("Books Status Updated", "Totally " + booksCount + " Books are Approved");
    }

    @PatchMapping("/reject")
    public ResponseEntity<ApiResponseEntity<String>> rejectAllBooks(@RequestBody BookIdsRequest request) {
        logger.info("Request Received to Reject books {}", request.getBookIds());

        int booksCount = service.rejectAllBooks(request.getBookIds());

        logger.info("Request Completed for Reject books");

        return ResponseEntityHelper
                .buildSuccessResponse("Books Status Updated", "Totally " + booksCount + " Books are Rejected");
    }

    @GetMapping("/author")
    public ResponseEntity<ApiResponseEntity<List<AuthorBookViewResponse>>> getAllAuthorBooks() {
        logger.info("Request Received to fetch books related to author");

        List<AuthorBookViewResponse> responses = service.getAuthorBooksAll();

        logger.info("Request Completed to fetch books related to author");
        return ResponseEntityHelper
                .buildSuccessResponse("Author Books fetched successfully", responses);
    }
}
