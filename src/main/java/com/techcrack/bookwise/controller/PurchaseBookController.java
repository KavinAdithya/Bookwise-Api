package com.techcrack.bookwise.controller;

import com.techcrack.bookwise.abstractions.CurrentUserService;
import com.techcrack.bookwise.abstractions.PurchaseBookService;
import com.techcrack.bookwise.dtos.purchasebook.request.PurchaseBookAmountCalculateRequest;
import com.techcrack.bookwise.dtos.purchasebook.request.PurchaseBookRequest;
import com.techcrack.bookwise.dtos.purchasebook.response.PurchaseBookAmountCalculateResponse;
import com.techcrack.bookwise.dtos.purchasebook.response.PurchaseBookResponse;
import com.techcrack.bookwise.entity.PurchaseBook;
import com.techcrack.bookwise.mapper.PurchaseBookMapper;
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

    @PostMapping("/purchase-books/calculate-amount")
    public ResponseEntity<ApiResponseEntity<PurchaseBookAmountCalculateResponse>> calculatePurchaseAmount(@RequestBody PurchaseBookAmountCalculateRequest request) {
       logger.info("Request Received tp calculate purchase amount for {}" , request);

       double amount = service.calculatePurchasePriceBook(request);

        PurchaseBookAmountCalculateResponse response = new PurchaseBookAmountCalculateResponse(amount);

        logger.info("Purchase Book Amount Calculation Request Completed Amount {}", amount);

        return ResponseEntityHelper
                .buildSuccessResponse("Amount Calculated Successfully", response);
    }
}
