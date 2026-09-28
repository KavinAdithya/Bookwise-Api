package com.techcrack.bookwise.abstractions;

import com.techcrack.bookwise.dtos.purchasebook.request.PurchasingBookOrderDetailRequest;
import com.techcrack.bookwise.dtos.purchasebook.request.PurchaseBookRequest;
import com.techcrack.bookwise.dtos.purchasebook.response.PurchaseBookViewResponse;
import com.techcrack.bookwise.dtos.purchasebook.response.PurchasedBookDetailResponse;
import com.techcrack.bookwise.dtos.purchasebook.response.PurchasingBookDetailResponse;
import com.techcrack.bookwise.entity.PurchaseBook;

import java.util.List;

public interface PurchaseBookService extends BasicCRUD<PurchaseBook> {
    PurchaseBook purchaseBook(PurchaseBookRequest request);
    PurchasingBookDetailResponse computePurchasingBookOrderDetails(PurchasingBookOrderDetailRequest request);
    List<PurchaseBookViewResponse> findAllPurchaseBooks();
    PurchasedBookDetailResponse getPurchaseBookDetailsById(long purchasedBookId);
}
