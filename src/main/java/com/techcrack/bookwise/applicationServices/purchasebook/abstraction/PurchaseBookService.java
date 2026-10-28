package com.techcrack.bookwise.applicationServices.purchasebook.abstraction;

import com.techcrack.bookwise.abstractions.BasicCRUD;
import com.techcrack.bookwise.applicationServices.purchasebook.dto.request.PurchasingBookOrderDetailRequest;
import com.techcrack.bookwise.applicationServices.purchasebook.dto.request.PurchaseBookRequest;
import com.techcrack.bookwise.applicationServices.purchasebook.dto.response.PurchaseBookViewResponse;
import com.techcrack.bookwise.applicationServices.purchasebook.dto.response.PurchasedBookDetailResponse;
import com.techcrack.bookwise.applicationServices.purchasebook.dto.response.PurchasingBookDetailResponse;
import com.techcrack.bookwise.applicationServices.purchasebook.entity.PurchaseBook;

import java.util.List;

public interface PurchaseBookService extends BasicCRUD<PurchaseBook> {
    PurchaseBook purchaseBook(PurchaseBookRequest request);
    PurchasingBookDetailResponse computePurchasingBookOrderDetails(PurchasingBookOrderDetailRequest request);
    List<PurchaseBookViewResponse> findAllPurchaseBooks();
    PurchasedBookDetailResponse getPurchaseBookDetailsById(long purchasedBookId);
}
