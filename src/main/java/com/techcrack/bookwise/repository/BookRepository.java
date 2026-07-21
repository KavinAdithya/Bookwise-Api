package com.techcrack.bookwise.repository;

import com.techcrack.bookwise.constans.Status;
import com.techcrack.bookwise.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

import static com.techcrack.bookwise.constans.RawQueries.CHANGE_STATUS_ALL_BOOKS;

public interface BookRepository extends JpaRepository<Book, Long> {
    boolean existsByISBN(String ISBN);
    boolean existsByTitle(String title);

    List<Book> findAllByBookStatusAndAvailableCopiesGreaterThanAndIsActiveTrue(Status bookStatus, int availableCopies);

    @Query(value = CHANGE_STATUS_ALL_BOOKS, nativeQuery = true)
    int changeStatusOfAllBooks(
            @Param("bookIds") List<Long> bookIds,
            @Param("isActive") boolean isActive,
            @Param("status") Status status,
            @Param("updatedBy") long updatedBy,
            @Param("updatedAt") LocalDateTime updatedAt
            );
}
