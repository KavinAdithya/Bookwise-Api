package com.techcrack.bookwise.controller;

import com.techcrack.bookwise.abstractions.BookService;
import com.techcrack.bookwise.constans.enums.BookStatus;
import com.techcrack.bookwise.dtos.book.response.*;
import com.techcrack.bookwise.mapper.BookMapper;
import com.techcrack.bookwise.dtos.book.request.BookIdsRequest;
import com.techcrack.bookwise.dtos.book.request.BookRegisterRequest;
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

    public BookController(BookService service, BookMapper mapper, CurrentUserService userSession) {
        super(BookController.class, service, mapper, userSession);
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

    // This is for Users view
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

    @GetMapping("/author/book/{id}")
    public ResponseEntity<ApiResponseEntity<AuthorBookDetailViewResponse>> getAuthorBookById(@PathVariable("id") long bookId) {
        logger.info("Request Received to fetch book detail for author");

        Book book = service.getBookById(bookId);

        AuthorBookDetailViewResponse response = mapper.mapToAuthorBookDetailViewResponse(book);

        logger.info("Request Completed to fetch book detail for author");

        return ResponseEntityHelper
                .buildSuccessResponse("Author Book Detail Fetched Successfully", response);
    }

    @GetMapping("/admin/books")
    public ResponseEntity<ApiResponseEntity<List<AdminViewBookResponse>>> getAllBooksForAdmin(@RequestParam("bookStatus") BookStatus status) {
        logger.info("Request Received to fetch all books for admin");

        List<AdminViewBookResponse> responses = service.getAllBooksForAdmin(status);

        logger.info("Request Completed to fetch all books for admin");

        return ResponseEntityHelper
                .buildSuccessResponse("Admin Books fetched Successfully", responses);
    }

    @GetMapping("/admin/book/{bookId}")
    public ResponseEntity<ApiResponseEntity<AdminViewBookDetailResponse>>  getBookDetailForAdmin(@PathVariable("bookId") long bookId) {
        logger.info("Request Received to fetch book details for admin");

        Book book = service.getBookById(bookId);

        AdminViewBookDetailResponse response = mapper.mapToAdminViewBookDetailResponse(book);

        logger.info("Request Completed to fetch book details for admin");

        return ResponseEntityHelper
                .buildSuccessResponse("Admin book details fetched", response);
    }
}
