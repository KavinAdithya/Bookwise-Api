package com.techcrack.bookwise.repository;

import com.techcrack.bookwise.constans.enums.Status;
import com.techcrack.bookwise.dtos.book.response.AuthorBookViewResponse;
import com.techcrack.bookwise.entity.Book;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

import static com.techcrack.bookwise.constans.queries.JPQLQueries.*;

public interface BookRepository extends JpaRepository<Book, Long> {
    boolean existsByISBN(String ISBN);
    boolean existsByTitle(String title);

    List<Book> findAllByBookStatusAndAvailableCopiesGreaterThanAndIsActiveTrue(Status bookStatus, int availableCopies);


    @Modifying
    @Transactional
    @Query(value = CHANGE_STATUS_ALL_BOOKS)
    int changeStatusOfAllBooks(
            @Param("bookIds") List<Long> bookIds,
            @Param("isActive") boolean isActive,
            @Param("status") Status status,
            @Param("updatedBy") long updatedBy,
            @Param("updatedAt") LocalDateTime updatedAt,
            @Param("publishDate") LocalDateTime publishDate
            );

    @Modifying
    @Transactional
    @Query(value = UPDATE_BOOK_QUANTITY)
    int updateBookQuantity(
            @Param("bookId") long bookId,
            @Param("quantity") int quantity,
            @Param("updatedBy") long updatedBy,
            @Param("updatedAt") LocalDateTime updatedAt);

    @Query(FETCH_BOOKS_FOR_AUTHOR)
    List<AuthorBookViewResponse> getAuthorBooksByAuthorId(long authorId);
}
