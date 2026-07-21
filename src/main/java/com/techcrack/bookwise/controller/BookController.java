package com.techcrack.bookwise.controller;

import com.techcrack.bookwise.abstractions.BookService;
import com.techcrack.bookwise.dtoMapper.BookHelper;
import com.techcrack.bookwise.dtos.BookIdsRequest;
import com.techcrack.bookwise.dtos.BookRegisterDTO;
import com.techcrack.bookwise.dtos.BookResponseDTO;
import com.techcrack.bookwise.dtos.PendingBookDTO;
import com.techcrack.bookwise.entity.Book;
import com.techcrack.bookwise.responseHelper.ApiResponseEntity;
import com.techcrack.bookwise.responseHelper.ResponseEntityHelper;
import org.hibernate.boot.model.source.spi.PluralAttributeIndexSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookController {
    private final BookService service;
    private final BookHelper helper;
    private final Logger logger;

    public BookController(BookService service, BookHelper helper) {
        this.service = service;
        this.helper = helper;
        this.logger = LoggerFactory.getLogger(BookController.class);
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

        service.approveAllBooks(request.getBookIds());

        logger.info("Request Completed for approve books");

        return ResponseEntityHelper
                .buildSuccessResponse("Books Status Updated", "All Books are Approved");
    }

    @PostMapping("/books/reject")
    public ResponseEntity<ApiResponseEntity<String>> rejectAllBooks(@RequestBody BookIdsRequest request) {
        logger.info("Request Received to Reject books {}", request.getBookIds());

        service.rejectAllBooks(request.getBookIds());

        logger.info("Request Completed for Reject books");

        return ResponseEntityHelper
                .buildSuccessResponse("Books Status Updated", "All Books are Rejected");
    }
}
