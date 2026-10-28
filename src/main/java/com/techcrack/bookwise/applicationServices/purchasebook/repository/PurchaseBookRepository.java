package com.techcrack.bookwise.applicationServices.purchasebook.repository;


import com.techcrack.bookwise.applicationServices.purchasebook.dto.response.PurchaseBookViewResponse;
import com.techcrack.bookwise.applicationServices.purchasebook.dto.response.PurchasedBookDetailResponse;
import com.techcrack.bookwise.applicationServices.purchasebook.entity.PurchaseBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import static com.techcrack.bookwise.applicationServices.purchasebook.repository.queries.PurchaseBookJPQLQueries.FIND_ALL_USER_PURCHASED_BOOK;
import static com.techcrack.bookwise.applicationServices.purchasebook.repository.queries.PurchaseBookJPQLQueries.GET_PURCHASED_BOOK_BY_ID;

public interface PurchaseBookRepository extends JpaRepository<PurchaseBook, Long> {
    @Query(FIND_ALL_USER_PURCHASED_BOOK)
    List<PurchaseBookViewResponse> findAllPurchaseBooksBasedOnUser(long userId);

    @Query(GET_PURCHASED_BOOK_BY_ID)
    PurchasedBookDetailResponse getPurchasedBookDetailById(long purchaseBookId);
}
