package com.techcrack.bookwise.controller;

import com.techcrack.bookwise.abstractions.BorrowBookService;
import com.techcrack.bookwise.constans.ApplicationData;
import com.techcrack.bookwise.dtos.borrowbook.layer.ReturnBookContext;
import com.techcrack.bookwise.dtos.borrowbook.request.BorrowBookRequest;
import com.techcrack.bookwise.dtos.borrowbook.request.ReturnBookRequest;
import com.techcrack.bookwise.dtos.borrowbook.response.BorrowBookResponseDTO;
import com.techcrack.bookwise.dtos.borrowbook.response.DueAmountResponse;
import com.techcrack.bookwise.dtos.borrowbook.response.ReturnBookResponse;
import com.techcrack.bookwise.entity.BorrowBook;
import com.techcrack.bookwise.helper.BorrowBookHelper;
import com.techcrack.bookwise.jwt.CurrentUserService;
import com.techcrack.bookwise.responseHelper.ApiResponseEntity;
import com.techcrack.bookwise.responseHelper.ResponseEntityHelper;
import com.techcrack.bookwise.utils.AbstractController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BorrowBookController extends AbstractController<BorrowBookController, BorrowBookService, BorrowBookHelper> {
    public BorrowBookController(BorrowBookService service, BorrowBookHelper helper, CurrentUserService userSession) {
        super(BorrowBookController.class, service, helper, userSession);
    }

    @PostMapping("/borrow-book")
    public ResponseEntity<ApiResponseEntity<BorrowBookResponseDTO>> borrowBook(@RequestBody BorrowBookRequest request) {
        logger.info("Request Received to borrow book {}", request);

        BorrowBook borrowBook = service.borrowBook(request);
        logger.info("Book has been Borrowed");

        BorrowBookResponseDTO responseDTO = helper.mapToBorrowBookResponseDTO(borrowBook);

        logger.info("Request completed for borrow book {}", request);
        return ResponseEntityHelper
                .buildSuccessResponse("Book Borrowed Successfully", responseDTO);
    }

    @GetMapping("/borrow-books/{borrowBookId}/fetch-book")
    public ResponseEntity<ApiResponseEntity<BorrowBookResponseDTO>> borrowBook(@PathVariable long borrowBookId) {
        logger.info("Request received to get borrow details for {}", borrowBookId);

        BorrowBook book = service.getBorrowDetails(borrowBookId);

        BorrowBookResponseDTO response = helper.mapToBorrowBookResponseDTO(book);

        logger.info("Request completed for get borrow details for {}", borrowBookId);
        return ResponseEntityHelper
                    .buildSuccessResponse(
                        "Borrow Details Fetched",
                            response
                    );
    }

    @GetMapping("/borrow-books/{borrowBookId}/calculate-due")
    public ResponseEntity<ApiResponseEntity<DueAmountResponse>> getDueAmount(@PathVariable long borrowBookId) {
        logger.info("Request received to calculate due amount for {}", borrowBookId);
        double dueAmount = service.calculateDueAmount(borrowBookId);

        DueAmountResponse response = helper.mapToDueAmountResponse(dueAmount);

        logger.info("Request Completed to calculate due amount");
        return ResponseEntityHelper
                .buildSuccessResponse("Due Amount Fetched", response);
    }

    @GetMapping("/borrow-books")
    public ResponseEntity<ApiResponseEntity<List<BorrowBookResponseDTO>>> fetchBorrowRequests() {
        logger.info("Request Received to fetch all borrow details of the user {}", ApplicationData.HARD_CODED_CURRENT_ID);

        List<BorrowBook> borrowBooks = service.getAllBorrowDetails();

        List<BorrowBookResponseDTO> responseDTOS = helper.mapToBorrowBookResponseDTOs(borrowBooks);

        logger.info("Request completed to fetch borrow details of a user");

        return ResponseEntityHelper
                .buildSuccessResponse("Borrow Books Fetched Successfully", responseDTOS);
    }

    @PostMapping("/return-book")
    public ResponseEntity<ApiResponseEntity<ReturnBookResponse>> returnBook(@RequestBody ReturnBookRequest request) {
        logger.info("Request received to return a book with {}", request);


        ReturnBookContext context = helper.mapToReturnBookContext(request);

        service.returnBook(context);

        ReturnBookResponse response = helper.mapToReturnBookResponse("Return Completed for borrow id " + context.borrowBookId());

        logger.info("Request Completed to return a book {}", request);
        return ResponseEntityHelper
                .buildSuccessResponse("Book Returned Successfully", response);
    }
}
