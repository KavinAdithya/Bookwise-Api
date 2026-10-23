package com.techcrack.bookwise.service.admin;

import com.techcrack.bookwise.constans.enums.IncomeType;
import com.techcrack.bookwise.exceptions.customized.InvalidDataException;
import com.techcrack.bookwise.exceptions.customized.InvalidDateRangeException;
import com.techcrack.bookwise.repository.AdminRevenueRepository;
import com.techcrack.bookwise.repository.AuthorRevenueRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdminRevenueCalculationServiceTest {
    @Mock
    private AdminRevenueRepository adminRevenueRepository;

    @Mock
    private AuthorRevenueRepository authorRevenueRepository;

    @InjectMocks
    private AdminRevenueCalculationService adminRevenueCalculationService;

    @Nested
    @DisplayName("Summing up total amount based on income type")
    class IncomeTypeCalculationTest {
        @Test
        public void calculateTotalAllRevenueTest() {
            when(
            adminRevenueRepository
                    .calculateTotalAdminRevenue(
                            any(LocalDateTime.class),
                            any(LocalDateTime.class),
                            any(Boolean.class),
                            any()
                    ))
                    .thenReturn(new BigDecimal("100.00"));
            BigDecimal res = adminRevenueCalculationService.calculateTotalAllRevenue(LocalDateTime.now().minusYears(1), LocalDateTime.now());

            assertEquals(100.00, res.doubleValue());
        }

        @Test
        public void calculateTotalAllRevenueInvalidDurationCase() {
            BigDecimal res = adminRevenueCalculationService.calculateTotalAllRevenue(LocalDateTime.now(), LocalDateTime.now().minusYears(1));

            assertEquals(0.00, res.doubleValue());


            verify(adminRevenueRepository, never()).calculateTotalAdminRevenue(any(), any(), any(Boolean.class), any());
        }

        @Test
        public void calculateTotalAllRevenueEdgeCase() {
            BigDecimal res =
                        adminRevenueCalculationService.calculateTotalAllRevenue(null, null);

            assertEquals(BigDecimal.ZERO, res);

            verify(adminRevenueRepository, never())
                    .calculateTotalAdminRevenue(
                            any(),
                            any(),
                            anyBoolean(),
                            isNull()
                    );
        }

        @Test
        public void calculateTotalRevenueBasedOnIncomeTypeTest() {
            BigDecimal res = adminRevenueCalculationService.calculateTotalRevenueBasedOnIncomeType(LocalDateTime.now().minusYears(1), LocalDateTime.now(), null);

            assertEquals(0, res.doubleValue());

            verifyNoInteractions(adminRevenueRepository);
        }

        @Test
        public void findAllIncomeTypeRevenuesTest() {
            when(
                    adminRevenueRepository
                            .calculateTotalAdminRevenue(
                                    any(LocalDateTime.class),
                                    any(LocalDateTime.class),
                                    any(Boolean.class),
                                    any()
                            ))
                    .thenReturn(new BigDecimal("100.00"));

            Map<IncomeType, BigDecimal> res = adminRevenueCalculationService.findAllIncomeTypeRevenues(LocalDateTime.now().minusYears(1), LocalDateTime.now(), IncomeType.UNKNOWN);

            assertEquals(3, res.size());
            assertEquals(300.00, res
                                            .values()
                                            .stream()
                                            .reduce(BigDecimal.valueOf(0.00), (a, b) -> BigDecimal.valueOf(a.doubleValue() + b.doubleValue()))
                                            .doubleValue());
        }

        @Test
        public void findAllIncomeTypeRevenuesInvalidDurationExceptionTest() {

            InvalidDateRangeException exception = assertThrows(InvalidDateRangeException.class, () ->  adminRevenueCalculationService
                                                                                .findAllIncomeTypeRevenues(
                                                                                        null,
                                                                                        null,
                                                                                        IncomeType.UNKNOWN
                                                                                )
            );

            assertEquals("Start date or end date is wrong.", exception.getMessage());
        }

        @Test
        public void findAllIncomeTypeRevenuesIncomeTypeNullExceptionTest() {

            InvalidDataException exception = assertThrows(InvalidDataException.class, () ->  adminRevenueCalculationService
                    .findAllIncomeTypeRevenues(
                            LocalDateTime.now().minusYears(1),
                            LocalDateTime.now(),
                            null
                    )
            );

            assertEquals("Invalid Income type.", exception.getMessage());
        }
    }
}