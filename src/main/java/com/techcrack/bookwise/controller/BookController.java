package com.techcrack.bookwise.controller;

import com.techcrack.bookwise.abstractions.BookService;
import com.techcrack.bookwise.dtoMapper.BookHelper;
import com.techcrack.bookwise.dtos.BookRegisterDTO;
import com.techcrack.bookwise.dtos.BookResponseDTO;
import com.techcrack.bookwise.entity.Book;
import com.techcrack.bookwise.responseHelper.ApiResponseEntity;
import com.techcrack.bookwise.responseHelper.ResponseEntityHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
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
}
