package com.techcrack.bookwise.controller;

import com.techcrack.bookwise.abstractions.BorrowBookService;
import com.techcrack.bookwise.dtos.book.response.BorrowBookConfirmationDetail;
import com.techcrack.bookwise.dtos.borrowbook.layer.ReturnBookContext;
import com.techcrack.bookwise.dtos.borrowbook.request.BorrowBookRequest;
import com.techcrack.bookwise.dtos.borrowbook.request.ReturnBookRequest;
import com.techcrack.bookwise.dtos.borrowbook.response.BorrowBookRegisterResponse;
import com.techcrack.bookwise.dtos.borrowbook.response.DueAmountResponse;
import com.techcrack.bookwise.dtos.borrowbook.response.ReturnBookResponse;
import com.techcrack.bookwise.entity.BorrowBook;
import com.techcrack.bookwise.mapper.BorrowBookMapper;
import com.techcrack.bookwise.abstractions.CurrentUserService;
import com.techcrack.bookwise.responseHelper.ApiResponseEntity;
import com.techcrack.bookwise.responseHelper.ResponseEntityHelper;
import com.techcrack.bookwise.utils.AbstractController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BorrowBookController extends AbstractController<BorrowBookController, BorrowBookService, BorrowBookMapper> {
    public BorrowBookController(BorrowBookService service, BorrowBookMapper helper, CurrentUserService userSession) {
        super(BorrowBookController.class, service, helper, userSession);
    }

    @PostMapping("/borrow-books/register")
    public ResponseEntity<ApiResponseEntity<BorrowBookRegisterResponse>> registerBorrowBook(@RequestBody BorrowBookRequest request) {
        logger.info("Request Received to borrow book {}", request);

        BorrowBook borrowBook = service.borrowBook(request);
        logger.info("Book has been Borrowed");

        BorrowBookRegisterResponse response = mapper.mapToBorrowBookRegisterResponse(borrowBook);

        logger.info("Request completed for borrow book {}", request);
        return ResponseEntityHelper
                .buildSuccessResponse("Book Borrowed Successfully", response);
    }

    @GetMapping("/borrow-books/{borrowBookId}")
    public ResponseEntity<ApiResponseEntity<BorrowBookRegisterResponse>> getBorrowBookById(@PathVariable long borrowBookId) {
        logger.info("Request received to get borrow details for {}", borrowBookId);

        BorrowBook book = service.getBorrowBookById(borrowBookId);

        BorrowBookRegisterResponse response = mapper.mapToBorrowBookRegisterResponse(book);

        logger.info("Request completed for get borrow details for {}", borrowBookId);
        return ResponseEntityHelper
                    .buildSuccessResponse(
                        "Borrow Details Fetched",
                            response
                    );
    }

    @GetMapping("/borrow-books/{borrowBookId}/calculate-due")
    public ResponseEntity<ApiResponseEntity<DueAmountResponse>> calculateDueAmount(@PathVariable long borrowBookId) {
        logger.info("Request received to calculate due amount for {}", borrowBookId);
        double dueAmount = service.calculateDueAmount(borrowBookId);

        DueAmountResponse response = mapper.mapToDueAmountResponse(dueAmount);

        logger.info("Request Completed to calculate due amount");
        return ResponseEntityHelper
                .buildSuccessResponse("Due Amount Fetched", response);
    }

    @GetMapping("/borrow-books")
    public ResponseEntity<ApiResponseEntity<List<BorrowBookRegisterResponse>>> getAllBorrowRequests() {
        logger.info("Request Received to fetch all borrow details of the user {}", userSession.getCurrentUserId());

        List<BorrowBook> borrowBooks = service.getAllBorrowBooks();

        List<BorrowBookRegisterResponse> responseDTOS = mapper.mapToBorrowBookRegisterResponses(borrowBooks);

        logger.info("Request completed to fetch borrow details of a user");

        return ResponseEntityHelper
                .buildSuccessResponse("Borrow Books Fetched Successfully", responseDTOS);
    }

    @PatchMapping("/borrow-books/return-book")
    public ResponseEntity<ApiResponseEntity<ReturnBookResponse>> returnBorrowBook(@RequestBody ReturnBookRequest request) {
        logger.info("Request received to return a book with {}", request);


        ReturnBookContext context = mapper.mapToReturnBookContext(request);

        service.returnBook(context);

        ReturnBookResponse response = mapper.mapToReturnBookResponse("Return Completed for borrow id " + context.borrowBookId());

        logger.info("Request Completed to return a book {}", request);
        return ResponseEntityHelper
                .buildSuccessResponse("Book Returned Successfully", response);
    }

    @GetMapping("/borrow-books/confirmation/{bookId}/{quantity}")
    public  ResponseEntity<ApiResponseEntity<BorrowBookConfirmationDetail>> getBorrowBookConfirmationDetails(@PathVariable("bookId") long bookId, @PathVariable("quantity") int quantity) {
        logger.info("Request received to get borrow book confirmation details");

        BorrowBookConfirmationDetail borrowBookConfirmationDetail = service.computeBorrowBookConfirmationDetails(
                new BorrowBookRequest(bookId, quantity)
        );

        logger.info("Request Completed to get borrow book confirmation details");

        return ResponseEntityHelper
                .buildSuccessResponse("Computed Borrow Book Confirmation Details", borrowBookConfirmationDetail);
    }
}
