package com.techcrack.bookwise.repository;


import com.techcrack.bookwise.dtos.purchasebook.response.PurchaseBookViewResponse;
import com.techcrack.bookwise.dtos.purchasebook.response.PurchasedBookDetailResponse;
import com.techcrack.bookwise.entity.PurchaseBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import static com.techcrack.bookwise.constans.queries.jpql.PurchaseBook.FIND_ALL_USER_PURCHASED_BOOK;
import static com.techcrack.bookwise.constans.queries.jpql.PurchaseBook.GET_PURCHASED_BOOK_BY_ID;

public interface PurchaseBookRepository extends JpaRepository<PurchaseBook, Long> {
    @Query(FIND_ALL_USER_PURCHASED_BOOK)
    List<PurchaseBookViewResponse> findAllPurchaseBooksBasedOnUser(long userId);

    @Query(GET_PURCHASED_BOOK_BY_ID)
    PurchasedBookDetailResponse getPurchasedBookDetailById(long purchaseBookId);
}
