package com.techcrack.bookwise.repository;

import com.techcrack.bookwise.entity.BorrowBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface BorrowBookRepository extends JpaRepository<BorrowBook, Long> {
    Optional<BorrowBook> findByBorrowDateAndBook_IdAndUser_Id(long bookId, long userId, LocalDateTime borrowDate);
}
