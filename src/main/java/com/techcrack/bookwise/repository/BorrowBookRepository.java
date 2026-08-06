package com.techcrack.bookwise.repository;

import com.techcrack.bookwise.entity.BorrowBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BorrowBookRepository extends JpaRepository<BorrowBook, Long> {
    Optional<BorrowBook> findByIdAndIsActiveTrueAndUser_Id(long id, long userId);
    List<BorrowBook> findByIsActiveTrueAndUser_Id(long userId);
    Optional<BorrowBook> findByIdAndIsActiveTrue(long id);
}
