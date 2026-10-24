package com.techcrack.bookwise.repository;

import com.techcrack.bookwise.dtos.author.sqlProjector.AuthorRevenueProjector;
import com.techcrack.bookwise.dtos.author.sqlProjector.BookQuantityProjector;
import com.techcrack.bookwise.entity.AuthorRevenue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

import static com.techcrack.bookwise.constans.queries.sql.AuthorRevenueSqlQueries.BOOKS_QUANTITY;
import static com.techcrack.bookwise.constans.queries.sql.AuthorRevenueSqlQueries.TOTAL_AUTHOR_REVENUE;

public interface AuthorRevenueRepository extends JpaRepository<AuthorRevenue, Long> {
    @Query(value = TOTAL_AUTHOR_REVENUE, nativeQuery = true)
    List<AuthorRevenueProjector> getTotalAuthorRevenue(LocalDateTime startDate, LocalDateTime endDate, boolean isAllIncomeType, String incomeType);

    @Query(value = BOOKS_QUANTITY, nativeQuery = true)
    List<BookQuantityProjector> getAllAuthorRevenueBooksQuantity(LocalDateTime startDate, LocalDateTime endDate, boolean isAllIncomeType, String incomeType);
}
