package com.techcrack.bookwise.applicationServices.admin;

import com.techcrack.bookwise.constans.enums.IncomeType;
import com.techcrack.bookwise.applicationServices.admin.dto.sqlProjector.AdminRevenueIncomeBasedProjector;
import com.techcrack.bookwise.applicationServices.author.dto.sqlProjector.AuthorRevenueProjector;
import com.techcrack.bookwise.exceptions.customized.InvalidDataException;
import com.techcrack.bookwise.exceptions.customized.InvalidDateRangeException;
import com.techcrack.bookwise.helper.CollectionHelper;
import com.techcrack.bookwise.mapper.RevenueCalculationMapper;
import com.techcrack.bookwise.applicationServices.admin.repository.AdminRevenueRepository;
import com.techcrack.bookwise.applicationServices.author.repository.AuthorRevenueRepository;
import com.techcrack.bookwise.applicationServices.admin.service.AdminRevenueCalculationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdminRevenueCalculationServiceTest {

    @Mock
    private AdminRevenueRepository adminRevenueRepository;

    @Mock
    private AuthorRevenueRepository authorRevenueRepository;

    @Mock
    private RevenueCalculationMapper mapper;

    private AdminRevenueCalculationService service;

    @BeforeEach
    void setUp() {
        CollectionHelper collectionHelper = new CollectionHelper();

        service = new AdminRevenueCalculationService(
                adminRevenueRepository,
                authorRevenueRepository,
                collectionHelper,
                mapper
        );
    }

    @Test
    void calculateTotalRevenueBasedOnIncomeType_invalidDates_returnsEmptyList() {
        LocalDateTime endDate = LocalDateTime.now();
        LocalDateTime startDate = endDate.plusDays(1);

        List<AdminRevenueIncomeBasedProjector> result =
                service.calculateTotalRevenueBasedOnIncomeType(
                        startDate,
                        endDate,
                        IncomeType.UNKNOWN
                );

        assertNotNull(result);
        assertTrue(result.isEmpty());

        verifyNoInteractions(adminRevenueRepository);
    }

    @Test
    void calculateTotalRevenueBasedOnIncomeType_unknownIncomeType_queriesAllTypes() {
        LocalDateTime start = LocalDateTime.of(2026, 1, 1, 0, 0);
        LocalDateTime end = LocalDateTime.of(2026, 1, 31, 23, 59);

        when(adminRevenueRepository.calculateTotalAdminRevenue(
                start, end, true, null
        )).thenReturn(List.of());

        List<AdminRevenueIncomeBasedProjector> result =
                service.calculateTotalRevenueBasedOnIncomeType(
                        start, end, IncomeType.UNKNOWN
                );

        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(adminRevenueRepository).calculateTotalAdminRevenue(
                start, end, true, null
        );
    }

    @Test
    void calculateTotalRevenueBasedOnIncomeType_specificType_queriesThatType() {
        LocalDateTime start = LocalDateTime.of(2026, 1, 1, 0, 0);
        LocalDateTime end = LocalDateTime.of(2026, 1, 31, 23, 59);

        IncomeType specificType = Arrays.stream(IncomeType.values())
                .filter(type -> type != IncomeType.UNKNOWN)
                .findFirst()
                .orElseThrow();

        when(adminRevenueRepository.calculateTotalAdminRevenue(
                start, end, false, specificType.name()
        )).thenReturn(List.of());

        service.calculateTotalRevenueBasedOnIncomeType(
                start, end, specificType
        );

        verify(adminRevenueRepository).calculateTotalAdminRevenue(
                start, end, false, specificType.name()
        );
    }

    @Test
    void findAllIncomeTypeRevenues_invalidDateRange_throwsException() {
        LocalDateTime start = LocalDateTime.of(2026, 2, 1, 0, 0);
        LocalDateTime end = LocalDateTime.of(2026, 1, 1, 0, 0);

        assertThrows(
                InvalidDateRangeException.class,
                () -> service.findAllIncomeTypeRevenues(
                        start, end, IncomeType.UNKNOWN
                )
        );

        verifyNoInteractions(adminRevenueRepository);
    }

    @Test
    void findAllIncomeTypeRevenues_nullIncomeType_throwsException() {
        LocalDateTime start = LocalDateTime.of(2026, 1, 1, 0, 0);
        LocalDateTime end = LocalDateTime.of(2026, 1, 31, 0, 0);

        assertThrows(
                InvalidDataException.class,
                () -> service.findAllIncomeTypeRevenues(
                        start, end, null
                )
        );

        verifyNoInteractions(adminRevenueRepository);
    }

    @Test
    void getTotalAuthorRevenue_nullIncomeType_throwsException() {
        assertThrows(
                InvalidDataException.class,
                () -> service.getTotalAuthorRevenue(
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        null
                )
        );

        verifyNoInteractions(authorRevenueRepository);
    }

    @Test
    void getTotalAuthorRevenue_unknownIncomeType_queriesAllTypes() {
        LocalDateTime start = LocalDateTime.of(2026, 1, 1, 0, 0);
        LocalDateTime end = LocalDateTime.of(2026, 1, 31, 0, 0);

        when(authorRevenueRepository.getTotalAuthorRevenue(
                start, end, true, null, false, null
        )).thenReturn(List.of());

        List<AuthorRevenueProjector> result =
                service.getTotalAuthorRevenue(
                        start, end, IncomeType.UNKNOWN
                );

        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(authorRevenueRepository).getTotalAuthorRevenue(
                start, end, true, null, false, null
        );
    }
}