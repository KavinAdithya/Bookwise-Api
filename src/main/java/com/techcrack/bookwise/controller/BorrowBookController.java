package com.techcrack.bookwise.controller;

import com.techcrack.bookwise.abstractions.BorrowBookService;
import com.techcrack.bookwise.dtos.borrowbook.request.BorrowBookRequestDTO;
import com.techcrack.bookwise.dtos.borrowbook.response.BorrowBookResponseDTO;
import com.techcrack.bookwise.entity.BorrowBook;
import com.techcrack.bookwise.helper.BorrowBookHelper;
import com.techcrack.bookwise.responseHelper.ApiResponseEntity;
import com.techcrack.bookwise.responseHelper.ResponseEntityHelper;
import com.techcrack.bookwise.utils.AbstractController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BorrowBookController extends AbstractController<BorrowBookController, BorrowBookService, BorrowBookHelper> {
    public BorrowBookController(BorrowBookService service, BorrowBookHelper helper) {
        super(BorrowBookController.class, service, helper);
    }

    @PostMapping("/borrow/book")
    public ResponseEntity<ApiResponseEntity<BorrowBookResponseDTO>> borrowBook(@RequestBody BorrowBookRequestDTO requestDTO) {
        logger.info("Request Received to borrow book {}", requestDTO);

        BorrowBook borrowBook = helper.mapToBorrowBook(requestDTO);

        borrowBook = service.borrowBook(borrowBook);
        logger.info("Book has been Borrowed");

        BorrowBookResponseDTO responseDTO = helper.mapToBorrowBookResponseDTO(borrowBook);

        logger.info("Request completed for borrow book {}", requestDTO);
        return ResponseEntityHelper
                .buildSuccessResponse("Book Borrowed Successfully", responseDTO);
    }
}
