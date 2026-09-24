package com.techcrack.bookwise.repository;

import com.techcrack.bookwise.dtos.book.response.BorrowBookConfirmationDetail;
import com.techcrack.bookwise.dtos.borrowbook.response.BorrowBookViewResponse;
import com.techcrack.bookwise.entity.BorrowBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.techcrack.bookwise.constans.queries.JPQLQueries.FIND_ALL_BORROW_BOOKS_USER;

public interface BorrowBookRepository extends JpaRepository<BorrowBook, Long> {
    Optional<BorrowBook> findByIdAndIsActiveTrueAndUser_Id(long id, long userId);

    Optional<BorrowBook> findByIdAndIsActiveTrue(long id);

    @Query(FIND_ALL_BORROW_BOOKS_USER)
    List<BorrowBookViewResponse> findAllBorrowsBasedOnUser(long userId);
}
