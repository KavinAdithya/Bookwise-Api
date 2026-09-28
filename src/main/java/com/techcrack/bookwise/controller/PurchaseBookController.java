package com.techcrack.bookwise.controller;

import com.techcrack.bookwise.abstractions.CurrentUserService;
import com.techcrack.bookwise.abstractions.PurchaseBookService;
import com.techcrack.bookwise.dtos.purchasebook.request.PurchaseBookRequest;
import com.techcrack.bookwise.dtos.purchasebook.request.PurchasingBookOrderDetailRequest;
import com.techcrack.bookwise.dtos.purchasebook.response.*;
import com.techcrack.bookwise.entity.PurchaseBook;
import com.techcrack.bookwise.mapper.PurchaseBookMapper;
import com.techcrack.bookwise.responseHelper.ApiResponseEntity;
import com.techcrack.bookwise.responseHelper.ResponseEntityHelper;
import com.techcrack.bookwise.utils.AbstractController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PurchaseBookController extends AbstractController<PurchaseBookController, PurchaseBookService, PurchaseBookMapper> {

    public PurchaseBookController(PurchaseBookService service, PurchaseBookMapper mapper, CurrentUserService userSession) {
        super(PurchaseBookController.class, service, mapper, userSession);
    }

    @PostMapping("/purchase-books/register")
    public ResponseEntity<ApiResponseEntity<PurchaseBookResponse>> registerPurchaseBook(@RequestBody PurchaseBookRequest request) {
        logger.info("Request Received to purchase a book {}", request);

        PurchaseBook purchaseBook = service.purchaseBook(request);

        logger.info("Book Purchase request completed {}", purchaseBook);

        PurchaseBookResponse response = mapper.mapToPurchaseBookResponse(purchaseBook);

        return ResponseEntityHelper
                .buildSuccessResponse("Book Purchased successfully", response);
    }

    @GetMapping("/purchase-books/calculate-amount")
    public ResponseEntity<ApiResponseEntity<PurchasingBookDetailResponse>> calculatePurchaseAmount(@RequestParam("bookId") long bookId, @RequestParam("quantity") int quantity) {
       logger.info("Request Received to calculate purchase amount for {}" , bookId);

       PurchasingBookDetailResponse response = service.computePurchasingBookOrderDetails(new PurchasingBookOrderDetailRequest(bookId, quantity));

        logger.info("Purchase Book Amount Calculation Request Completed  {}", response);

        return ResponseEntityHelper
                .buildSuccessResponse("Amount Calculated Successfully", response);
    }

    @GetMapping("/user/me/purchase-books")
    public ResponseEntity<ApiResponseEntity<List<PurchaseBookViewResponse>>> getAllUserPurchaseBooks() {
        logger.info("Request received to get all user purchase books");

        List<PurchaseBookViewResponse> purchaseBookViewResponses = service.findAllPurchaseBooks();

        logger.info("Request completed to get all user purchase books");

        return ResponseEntityHelper
                .buildSuccessResponse("Purchase Books Fetched", purchaseBookViewResponses);
    }

    @GetMapping("/user/me/purchase-books/{purchaseBookId}")
    public ResponseEntity<ApiResponseEntity<PurchasedBookDetailResponse>> getPurchasedBookDetail(@PathVariable("purchaseBookId") long purchaseBookId) {
        logger.info("Request Received to fetch purchased book details with is {}", purchaseBookId);

        PurchasedBookDetailResponse purchasedBookDetailResponse = service.getPurchaseBookDetailsById(purchaseBookId);

        logger.info("Request completed to fetch purchased book details");

        return ResponseEntityHelper
                .buildSuccessResponse("Purchased Book Detail Fetched", purchasedBookDetailResponse);
    }
}