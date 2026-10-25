package com.techcrack.bookwise.service.author;

import com.techcrack.bookwise.constans.enums.IncomeType;
import com.techcrack.bookwise.dtos.author.context.AuthorIncomeTypeBasedRevenue;
import com.techcrack.bookwise.dtos.author.context.AuthorRevenueSummary;
import com.techcrack.bookwise.dtos.author.context.AuthorRevenueSummaryRequest;
import com.techcrack.bookwise.dtos.author.sqlProjector.AuthorRevenueProjector;
import com.techcrack.bookwise.dtos.author.sqlProjector.BookQuantityProjector;
import com.techcrack.bookwise.exceptions.customized.InvalidDataException;
import com.techcrack.bookwise.helper.CollectionHelper;
import com.techcrack.bookwise.mapper.RevenueCalculationMapper;
import com.techcrack.bookwise.repository.AuthorRevenueRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthorRevenueCalculationServiceTest {

    @Mock
    private AuthorRevenueRepository repository;

    @Mock
    private RevenueCalculationMapper mapper;

    private AuthorRevenueCalculationService service;

    @BeforeEach
    void setUp() {
        service = new AuthorRevenueCalculationService(
                repository,
                new CollectionHelper(),
                mapper
        );
    }

    @Test
    void computeSummary_nullAuthorId_throwsException() {
        AuthorRevenueSummaryRequest request = validRequest();

        assertThrows(
                InvalidDataException.class,
                () -> service.computeAuthorRevenueSummaryForDashboard(
                        null, request
                )
        );

        verifyNoInteractions(repository, mapper);
    }

    @Test
    void getTotalAuthorRevenue_unknownType_passesCorrectArguments() {
        Long authorId = 10L;
        LocalDateTime start = LocalDateTime.of(2026, 1, 1, 0, 0);
        LocalDateTime end = LocalDateTime.of(2026, 1, 31, 0, 0);

        when(repository.getTotalAuthorRevenue(
                start, end, true, null, true, authorId
        )).thenReturn(List.of());

        List<AuthorRevenueProjector> result =
                service.getTotalAuthorRevenue(
                        authorId, start, end, IncomeType.UNKNOWN
                );

        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(repository).getTotalAuthorRevenue(
                start, end, true, null, true, authorId
        );
    }

    @Test
    void getTotalAuthorRevenueBookCount_unknownType_passesCorrectArguments() {
        Long authorId = 10L;
        LocalDateTime start = LocalDateTime.of(2026, 1, 1, 0, 0);
        LocalDateTime end = LocalDateTime.of(2026, 1, 31, 0, 0);

        when(repository.getAllAuthorRevenueBooksQuantity(
                start, end, true, null, true, authorId
        )).thenReturn(List.of());

        List<BookQuantityProjector> result =
                service.getTotalAuthorRevenueBookCount(
                        authorId, start, end, IncomeType.UNKNOWN
                );

        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(repository).getAllAuthorRevenueBooksQuantity(
                start, end, true, null, true, authorId
        );
    }

    private AuthorRevenueSummaryRequest validRequest() {
        return new AuthorRevenueSummaryRequest(LocalDateTime.now().minusYears(1), LocalDateTime.now(), IncomeType.UNKNOWN);
    }

    @Test
    void computeSummary_nonPositiveAuthorId_throwsException() {
        var request = validRequest();

        assertAll(
                () -> assertThrows(
                        InvalidDataException.class,
                        () -> service.computeAuthorRevenueSummaryForDashboard(
                                0L, request
                        )
                ),
                () -> assertThrows(
                        InvalidDataException.class,
                        () -> service.computeAuthorRevenueSummaryForDashboard(
                                -1L, request
                        )
                )
        );

        verifyNoInteractions(repository, mapper);
    }

    @Test
    void computeSummary_nullRequest_throwsException() {
        assertThrows(
                InvalidDataException.class,
                () -> service.computeAuthorRevenueSummaryForDashboard(
                        1L, null
                )
        );

        verifyNoInteractions(repository, mapper);
    }

    @Test
    void computeSummary_nullIncomeType_throwsException() {
        var request = new AuthorRevenueSummaryRequest(
                LocalDateTime.of(2026, 1, 1, 0, 0),
                LocalDateTime.of(2026, 1, 31, 23, 59),
                null
        );

        assertThrows(
                InvalidDataException.class,
                () -> service.computeAuthorRevenueSummaryForDashboard(
                        1L, request
                )
        );

        verifyNoInteractions(repository, mapper);
    }


    @Test
    void computeSummary_validData_returnsCorrectTotals() {
        Long authorId = 10L;

        LocalDateTime start =
                LocalDateTime.of(2026, 1, 1, 0, 0);
        LocalDateTime end =
                LocalDateTime.of(2026, 1, 31, 23, 59);

        IncomeType incomeType = Arrays.stream(IncomeType.values())
                .filter(type -> type != IncomeType.UNKNOWN)
                .findFirst()
                .orElseThrow();

        var request = new AuthorRevenueSummaryRequest(
                start, end, incomeType
        );

        AuthorRevenueProjector revenueProjector =
                mock(AuthorRevenueProjector.class);

        when(revenueProjector.getIncomeType())
                .thenReturn(incomeType.name());
        when(revenueProjector.getTotalRevenue())
                .thenReturn(new BigDecimal("1000.00"));
        when(revenueProjector.getRevenueClaimed())
                .thenReturn(new BigDecimal("600.00"));
        when(revenueProjector.getRevenueNotClaimed())
                .thenReturn(new BigDecimal("400.00"));

        BookQuantityProjector quantityProjector =
                mock(BookQuantityProjector.class);

        when(quantityProjector.getBorrowBookCount()).thenReturn(12L);
        when(quantityProjector.getPurchaseBookCount()).thenReturn(5L);

        AuthorIncomeTypeBasedRevenue mappedRevenue =
                mock(AuthorIncomeTypeBasedRevenue.class);


        when(repository.getTotalAuthorRevenue(
                start, end, false, incomeType.name(), true, authorId
        )).thenReturn(List.of(revenueProjector));

        when(repository.getAllAuthorRevenueBooksQuantity(
                start, end, false, incomeType.name(), true, authorId
        )).thenReturn(List.of(quantityProjector));

        when(mapper.mapToAuthorIncomeTypeBasedRevenue(
                incomeType, revenueProjector
        )).thenReturn(mappedRevenue);

        AuthorRevenueSummary result =
                service.computeAuthorRevenueSummaryForDashboard(
                        authorId, request
                );

        assertNotNull(result);

        assertEquals(start, result.startDate());
        assertEquals(end, result.endDate());

        assertEquals(12L, result.totalBooksSold());
        assertEquals(5L, result.totalBooksBorrowed());

        System.out.println(result);
        assertEquals(
                0,
                new BigDecimal("1000.00")
                        .compareTo(result.totalAuthorRevenue())
        );

        assertEquals(
                0,
                new BigDecimal("600.00")
                        .compareTo(result.totalAuthorRevenueReleased())
        );

        assertEquals(
                0,
                new BigDecimal("400.00")
                        .compareTo(result.totalAuthorRevenueHold())
        );

        assertEquals(1, result.incomeTypeBasedRevenues().size());

        verify(mapper).mapToAuthorIncomeTypeBasedRevenue(
                incomeType, revenueProjector
        );
    }

    @Test
    void computeSummary_unknownIncomeType_skipsProjector() {
        var request = validRequest();
        Long authorId = 10L;

        AuthorRevenueProjector projector =
                mock(AuthorRevenueProjector.class);

        when(projector.getIncomeType())
                .thenReturn("INVALID_INCOME_TYPE");


        when(repository.getTotalAuthorRevenue(
                request.startDate(),
                request.endDate(),
                true,
                null,
                true,
                authorId
        )).thenReturn(List.of(projector));

        when(repository.getAllAuthorRevenueBooksQuantity(
                request.startDate(),
                request.endDate(),
                true,
                null,
                true,
                authorId
        )).thenReturn(List.of());

        AuthorRevenueSummary result =
                service.computeAuthorRevenueSummaryForDashboard(
                        authorId, request
                );

        assertEquals(
                0,
                BigDecimal.ZERO.compareTo(result.totalAuthorRevenue())
        );

        assertTrue(result.incomeTypeBasedRevenues().isEmpty());

        verifyNoInteractions(mapper);
    }
}