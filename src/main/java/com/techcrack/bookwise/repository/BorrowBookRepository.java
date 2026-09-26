package com.techcrack.bookwise.repository;

import com.techcrack.bookwise.dtos.borrowbook.response.BorrowBookDetailView;
import com.techcrack.bookwise.dtos.borrowbook.response.BorrowBookViewResponse;
import com.techcrack.bookwise.entity.BorrowBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

import static com.techcrack.bookwise.constans.queries.JPQLQueries.FIND_ALL_BORROW_BOOKS_USER;
import static com.techcrack.bookwise.constans.queries.JPQLQueries.GET_BORROW_BOOK_DETAIL_VIEW;

public interface BorrowBookRepository extends JpaRepository<BorrowBook, Long> {
    Optional<BorrowBook> findByIdAndIsActiveTrueAndUser_Id(long id, long userId);

    Optional<BorrowBook> findByIdAndIsActiveTrue(long id);

    @Query(FIND_ALL_BORROW_BOOKS_USER)
    List<BorrowBookViewResponse> findAllBorrowsBasedOnUser(long userId);

    @Query(GET_BORROW_BOOK_DETAIL_VIEW)
    BorrowBookDetailView getBorrowBookDetailViewByBorrowBookId(long borrowBookId);
}
