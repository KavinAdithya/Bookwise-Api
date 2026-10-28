package com.techcrack.bookwise.applicationServices.admin.service;

import com.techcrack.bookwise.constans.enums.IncomeType;
import com.techcrack.bookwise.applicationServices.admin.dto.sqlProjector.AdminRevenueIncomeBasedProjector;
import com.techcrack.bookwise.applicationServices.admin.dto.context.AdminRevenueSummary;
import com.techcrack.bookwise.applicationServices.admin.dto.context.AdminIncomeTypeBasedRevenue;
import com.techcrack.bookwise.applicationServices.author.dto.sqlProjector.AuthorRevenueProjector;
import com.techcrack.bookwise.applicationServices.author.dto.sqlProjector.BookQuantityProjector;
import com.techcrack.bookwise.exceptions.customized.InvalidDataException;
import com.techcrack.bookwise.exceptions.customized.InvalidDateRangeException;
import com.techcrack.bookwise.helper.CollectionHelper;
import com.techcrack.bookwise.mapper.RevenueCalculationMapper;
import com.techcrack.bookwise.applicationServices.admin.repository.AdminRevenueRepository;
import com.techcrack.bookwise.applicationServices.author.repository.AuthorRevenueRepository;
import com.techcrack.bookwise.utils.AbstractLogger;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class AdminRevenueCalculationService extends AbstractLogger<AdminRevenueCalculationService> {

    private final AdminRevenueRepository adminRevenueRepository;
    private final AuthorRevenueRepository authorRevenueRepository;
    private final CollectionHelper collectionHelper;
    private final RevenueCalculationMapper mapper;

    // Instance data
    private final Map<String, IncomeType> incomeTypeMap;

    public AdminRevenueCalculationService(
                                            AdminRevenueRepository adminRevenueRepository,
                                            AuthorRevenueRepository authorRevenueRepository,
                                            CollectionHelper collectionHelper,
                                            RevenueCalculationMapper mapper
                                        ) {
        super(AdminRevenueCalculationService.class);
        this.adminRevenueRepository = adminRevenueRepository;
        this.authorRevenueRepository = authorRevenueRepository;
        this.collectionHelper = collectionHelper;
        this.mapper = mapper;
        this.incomeTypeMap = collectionHelper.convertListIntoMap(Arrays.stream(IncomeType.values()).toList(), IncomeType::name);
    }

    public BigDecimal computeAdminTotalRevenue() {
        final LocalDateTime startDate = LocalDateTime.now().minusYears(10);
        final LocalDateTime endDate = LocalDateTime.now();
        final IncomeType incomeType = IncomeType.UNKNOWN;

        final Map<String, AdminRevenueIncomeBasedProjector> totalRevenue = collectionHelper.convertListIntoMap(
                findAllIncomeTypeRevenues(startDate, endDate, incomeType),
                AdminRevenueIncomeBasedProjector::getIncomeType
        );

        final Map<String, AuthorRevenueProjector> authorRevenue = collectionHelper.convertListIntoMap(
                getTotalAuthorRevenue(startDate, endDate, incomeType),
                AuthorRevenueProjector::getIncomeType);

        BigDecimal overallProfitOrLoss = BigDecimal.ZERO;

        for (String curIncomeType : totalRevenue.keySet()) {
            if (!incomeTypeMap.containsKey(curIncomeType))
                continue;

            final AdminRevenueIncomeBasedProjector curIncomeTypeAdminRevenue = totalRevenue.get(curIncomeType);
            final AuthorRevenueProjector curIncomeTypeAuthorRevenue = authorRevenue.getOrDefault(curIncomeType, null);
            final BigDecimal currentProfitOrLoss = curIncomeTypeAdminRevenue.getTotalIncome().subtract(curIncomeTypeAuthorRevenue == null ? BigDecimal.ZERO : curIncomeTypeAuthorRevenue.getTotalRevenue());

            overallProfitOrLoss = overallProfitOrLoss.add(currentProfitOrLoss);
        }

        return overallProfitOrLoss;
    }
    @Cacheable
    public AdminRevenueSummary getRevenueBasedOnIncomeType(LocalDateTime startDate, LocalDateTime endDate, IncomeType incomeType) {

        final Map<String, AdminRevenueIncomeBasedProjector> totalRevenue = collectionHelper.convertListIntoMap(
                                                                                                        findAllIncomeTypeRevenues(startDate, endDate, incomeType),
                                                                                                        AdminRevenueIncomeBasedProjector::getIncomeType
                                                                                                    );

        final Map<String, AuthorRevenueProjector> authorRevenue = collectionHelper.convertListIntoMap(
                                                                            getTotalAuthorRevenue(startDate, endDate, incomeType),
                                                                            AuthorRevenueProjector::getIncomeType);
        final List<AdminIncomeTypeBasedRevenue> incomeTypeBasedRevenues = new ArrayList<>();

        BigDecimal overallTotalRevenue = BigDecimal.ZERO;
        BigDecimal overallAuthorRevenue = BigDecimal.ZERO;
        BigDecimal overallAuthorRevenueClaimed = BigDecimal.ZERO;
        BigDecimal overallAuthorRevenueNotClaimed = BigDecimal.ZERO;
        BigDecimal overallProfitOrLoss = BigDecimal.ZERO;

        for (String curIncomeType : totalRevenue.keySet()) {
            if (!incomeTypeMap.containsKey(curIncomeType))
                continue;

            final AdminRevenueIncomeBasedProjector curIncomeTypeAdminRevenue = totalRevenue.get(curIncomeType);
            final AuthorRevenueProjector curIncomeTypeAuthorRevenue = authorRevenue.getOrDefault(curIncomeType, null);
            final BigDecimal currentProfitOrLoss = curIncomeTypeAdminRevenue.getTotalIncome().subtract(curIncomeTypeAuthorRevenue == null ? BigDecimal.ZERO : curIncomeTypeAuthorRevenue.getTotalRevenue());

            overallTotalRevenue = overallTotalRevenue.add(curIncomeTypeAdminRevenue.getTotalIncome());

            if (curIncomeTypeAuthorRevenue != null) {
                overallAuthorRevenue = overallAuthorRevenue.add(curIncomeTypeAuthorRevenue.getTotalRevenue());
                overallAuthorRevenueClaimed = overallAuthorRevenueClaimed.add(curIncomeTypeAuthorRevenue.getRevenueClaimed());
                overallAuthorRevenueNotClaimed = overallAuthorRevenueNotClaimed.add(curIncomeTypeAuthorRevenue.getRevenueNotClaimed());
            }
            overallProfitOrLoss = overallProfitOrLoss.add(currentProfitOrLoss);

            incomeTypeBasedRevenues.add(mapper.mapToAdminIncomeTypeBasedRevenue(
                incomeTypeMap.get(curIncomeType),
                curIncomeTypeAdminRevenue,
                curIncomeTypeAuthorRevenue,
                currentProfitOrLoss
            ));
        }

        List<BookQuantityProjector> bookQuantityProjectors = getTotalAuthorRevenueBookCount(startDate, endDate, incomeType);

        long totalBooksBorrowed = 0;
        long totalBooksSold = 0;

        for (BookQuantityProjector bookQuantityProjector : bookQuantityProjectors) {
            totalBooksSold += bookQuantityProjector.getPurchaseBookCount();
            totalBooksBorrowed += bookQuantityProjector.getBorrowBookCount();
        }

        return new AdminRevenueSummary(
                startDate,
                endDate,
                totalBooksSold,
                totalBooksBorrowed,
                overallTotalRevenue,
                overallAuthorRevenue,
                overallAuthorRevenueClaimed,
                overallAuthorRevenueNotClaimed,
                overallProfitOrLoss,
                incomeTypeBasedRevenues
        );
    }

    public List<BookQuantityProjector> getTotalAuthorRevenueBookCount(LocalDateTime startDate, LocalDateTime endDate, IncomeType incomeType) {
        if (incomeType == null)
            throw new InvalidDataException("Income Type is invalid");

        return authorRevenueRepository.getAllAuthorRevenueBooksQuantity(
                startDate,
                endDate,
                incomeType == IncomeType.UNKNOWN,
                incomeType == IncomeType.UNKNOWN ? null : incomeType.name(),
                false,
                null
        );
    }

    public List<AuthorRevenueProjector> getTotalAuthorRevenue(LocalDateTime startDate, LocalDateTime endDate, IncomeType incomeType) {
        if (incomeType == null)
            throw new InvalidDataException("Income Type is invalid");

        return authorRevenueRepository.getTotalAuthorRevenue(
            startDate,
            endDate,
            incomeType == IncomeType.UNKNOWN,
            incomeType == IncomeType.UNKNOWN ? null : incomeType.name(),
            false,
            null
        );
    }

    public List<AdminRevenueIncomeBasedProjector> findAllIncomeTypeRevenues(LocalDateTime startDate, LocalDateTime endDate, IncomeType requestedIncomeType) {
        if (startDate == null || endDate == null || startDate.isAfter(endDate)) {
            throw new InvalidDateRangeException("Start date or end date is wrong.");
        }

        if (requestedIncomeType == null)
            throw new InvalidDataException("Invalid Income type.");

        return calculateTotalRevenueBasedOnIncomeType(startDate, endDate, requestedIncomeType);
    }

    public List<AdminRevenueIncomeBasedProjector> calculateTotalRevenueBasedOnIncomeType(LocalDateTime startDate, LocalDateTime endDate, IncomeType incomeType) {
        if (startDate == null || endDate == null || incomeType == null || startDate.isAfter(endDate))
            return new ArrayList<>();

        return adminRevenueRepository
                .calculateTotalAdminRevenue(
                        startDate,
                        endDate,
                        incomeType == IncomeType.UNKNOWN,
                        incomeType == IncomeType.UNKNOWN ? null : incomeType.name()
                );
    }
}