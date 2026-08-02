package com.techcrack.bookwise.controller;

import com.techcrack.bookwise.abstractions.BorrowBookService;
import com.techcrack.bookwise.dtos.borrowbook.layer.BorrowBookContext;
import com.techcrack.bookwise.dtos.borrowbook.request.BorrowBookRequestDTO;
import com.techcrack.bookwise.dtos.borrowbook.request.FetchBorrowBookRequest;
import com.techcrack.bookwise.dtos.borrowbook.request.UserBasedBorrowBookRequest;
import com.techcrack.bookwise.dtos.borrowbook.response.BorrowBookResponseDTO;
import com.techcrack.bookwise.dtos.borrowbook.response.DueAmountResponse;
import com.techcrack.bookwise.entity.BorrowBook;
import com.techcrack.bookwise.helper.BorrowBookHelper;
import com.techcrack.bookwise.responseHelper.ApiResponseEntity;
import com.techcrack.bookwise.responseHelper.ResponseEntityHelper;
import com.techcrack.bookwise.utils.AbstractController;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BorrowBookController extends AbstractController<BorrowBookController, BorrowBookService, BorrowBookHelper> {
    public BorrowBookController(BorrowBookService service, BorrowBookHelper helper) {
        super(BorrowBookController.class, service, helper);
    }

    @PostMapping("/borrow-book")
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

    @PostMapping("/borrow-books/fetch")
    public ResponseEntity<ApiResponseEntity<BorrowBookResponseDTO>> borrowBook(@RequestBody FetchBorrowBookRequest request) {
        logger.info("Request received to get borrow details for {}", request);

        BorrowBookContext context = helper.mapToBorrowBookContext(request);

        BorrowBook book = service.getBorrowDetails(context);

        BorrowBookResponseDTO response = helper.mapToBorrowBookResponseDTO(book);

        logger.info("Request completed for get borrow details for {}", context);
        return ResponseEntityHelper
                    .buildSuccessResponse(
                        "Borrow Details Fetched",
                            response
                    );
    }

    @PostMapping("/borrow-books/calculate-due")
    public ResponseEntity<ApiResponseEntity<DueAmountResponse>> getDueAmount(@RequestBody FetchBorrowBookRequest request) {
        logger.info("Request received to calculate due amount for {}", request);
        double dueAmount = service.calculateDueAmount(helper.mapToBorrowBookContext(request));

        DueAmountResponse response = helper.mapToDueAmountResponse(dueAmount);

        logger.info("Request Completed to calculate due amount");
        return ResponseEntityHelper
                .buildSuccessResponse("Due Amount Fetched", response);
    }

    @PostMapping("/borrow-books")
    public ResponseEntity<ApiResponseEntity<List<BorrowBookResponseDTO>>> fetchBorrowRequests(@RequestBody UserBasedBorrowBookRequest request) {
        logger.info("Request Received to fetch all borrow details of the user {}", request);

        List<BorrowBook> borrowBooks = service.getBorrowDetails(request.userId());

        List<BorrowBookResponseDTO> responseDTOS = helper.mapToBorrowBookResponseDTOs(borrowBooks);

        logger.info("Request completed to fetch borrow details of a user");

        return ResponseEntityHelper
                .buildSuccessResponse("Borrow Books Fetched Successfully", responseDTOS);
    }
}
