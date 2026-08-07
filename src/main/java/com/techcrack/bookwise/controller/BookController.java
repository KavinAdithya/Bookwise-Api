package com.techcrack.bookwise.controller;

import com.techcrack.bookwise.abstractions.BookService;
import com.techcrack.bookwise.helper.BookHelper;
import com.techcrack.bookwise.dtos.book.request.BookIdsRequest;
import com.techcrack.bookwise.dtos.book.request.BookRegisterDTO;
import com.techcrack.bookwise.dtos.book.response.BookResponseDTO;
import com.techcrack.bookwise.dtos.book.request.PendingBookDTO;
import com.techcrack.bookwise.entity.Book;
import com.techcrack.bookwise.jwt.CurrentUserService;
import com.techcrack.bookwise.responseHelper.ApiResponseEntity;
import com.techcrack.bookwise.responseHelper.ResponseEntityHelper;
import com.techcrack.bookwise.utils.AbstractController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookController extends AbstractController<BookController, BookService, BookHelper> {

    public BookController(BookService service, BookHelper helper, CurrentUserService userSession) {
        super(BookController.class, service, helper, userSession);
    }

    @PostMapping("/book/register")
    public ResponseEntity<ApiResponseEntity<BookResponseDTO>> register(@RequestBody BookRegisterDTO bookRegisterDTO)  {
        logger.info("Request Received for register a new book with {}", bookRegisterDTO.getTitle());

        Book book = helper.mapToBook(bookRegisterDTO);

        book = service.register(book);

        BookResponseDTO responseDTO = helper.mapToBookResponseDTO(book);

        logger.info("Request Completed for register a new book with {}", bookRegisterDTO.getTitle());

        return ResponseEntityHelper.buildSuccessResponse("Book Registered Successfully", responseDTO);
    }

    @GetMapping("/books")
    public ResponseEntity<ApiResponseEntity<List<BookResponseDTO>>> getAllBooksForView() {
        logger.info("Get all books request received");

        List<Book> books = service.getAllApprovedAndAvailableBooks();

        List<BookResponseDTO> response = helper.mapToBookResponseDTOs(books);

        logger.info("Get All Books request completed");
        return ResponseEntityHelper
                .buildSuccessResponse(
                        "Books Fetched Successfully",
                        response
                );
    }

    @GetMapping("/books/status/pending")
    public ResponseEntity<ApiResponseEntity<List<PendingBookDTO>>> getAllPendingBooks() {
        logger.info("Request Received to get all pending books");

        List<Book> books = service.getAllPendingBooks();

        List<PendingBookDTO> response = helper.mapToPendingBookDTOs(books);

        logger.info("Request Completed to get all pending books");

        return ResponseEntityHelper
                .buildSuccessResponse(
                        "Pending Books Fetched Successfully",
                        response
                );
    }

    @PostMapping("/books/approve")
    public ResponseEntity<ApiResponseEntity<String>> approveAllBooks(@RequestBody BookIdsRequest request) {
        logger.info("Request Received to approve books {}", request.getBookIds());

        int booksCount = service.approveAllBooks(request.getBookIds());

        logger.info("Request Completed for approve books");

        return ResponseEntityHelper
                .buildSuccessResponse("Books Status Updated", "Totally " + booksCount + " Books are Rejected");
    }

    @PostMapping("/books/reject")
    public ResponseEntity<ApiResponseEntity<String>> rejectAllBooks(@RequestBody BookIdsRequest request) {
        logger.info("Request Received to Reject books {}", request.getBookIds());

        int booksCount = service.rejectAllBooks(request.getBookIds());

        logger.info("Request Completed for Reject books");

        return ResponseEntityHelper
                .buildSuccessResponse("Books Status Updated", "Totally " + booksCount + " Books are Rejected");
    }
}
