package com.techcrack.bookwise.applicationServices.author.repository;

import com.techcrack.bookwise.applicationServices.author.dto.sqlProjector.AuthorRevenueProjector;
import com.techcrack.bookwise.applicationServices.author.dto.sqlProjector.BookQuantityProjector;
import com.techcrack.bookwise.applicationServices.author.entity.AuthorRevenue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

import static com.techcrack.bookwise.applicationServices.author.repository.queries.AuthorRevenueSqlQueries.BOOKS_QUANTITY;
import static com.techcrack.bookwise.applicationServices.author.repository.queries.AuthorRevenueSqlQueries.TOTAL_AUTHOR_REVENUE;

public interface AuthorRevenueRepository extends JpaRepository<AuthorRevenue, Long> {
    @Query(value = TOTAL_AUTHOR_REVENUE, nativeQuery = true)
    List<AuthorRevenueProjector> getTotalAuthorRevenue(LocalDateTime startDate, LocalDateTime endDate, boolean isAllIncomeType, String incomeType, boolean isAuthorLevel, Long authorId);

    @Query(value = BOOKS_QUANTITY, nativeQuery = true)
    List<BookQuantityProjector> getAllAuthorRevenueBooksQuantity(LocalDateTime startDate, LocalDateTime endDate, boolean isAllIncomeType, String incomeType , boolean isAuthorLevel, Long authorId);
}
