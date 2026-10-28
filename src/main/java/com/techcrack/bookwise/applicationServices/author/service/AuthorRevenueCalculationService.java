package com.techcrack.bookwise.applicationServices.author.service;

import com.techcrack.bookwise.constans.enums.IncomeType;
import com.techcrack.bookwise.applicationServices.author.dto.context.AuthorIncomeTypeBasedRevenue;
import com.techcrack.bookwise.applicationServices.author.dto.context.AuthorRevenueSummary;
import com.techcrack.bookwise.applicationServices.author.dto.context.AuthorRevenueSummaryRequest;
import com.techcrack.bookwise.applicationServices.author.dto.sqlProjector.AuthorRevenueProjector;
import com.techcrack.bookwise.applicationServices.author.dto.sqlProjector.BookQuantityProjector;
import com.techcrack.bookwise.exceptions.customized.InvalidDataException;
import com.techcrack.bookwise.helper.CollectionHelper;
import com.techcrack.bookwise.mapper.RevenueCalculationMapper;
import com.techcrack.bookwise.applicationServices.author.repository.AuthorRevenueRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class AuthorRevenueCalculationService {
    private final AuthorRevenueRepository repository;
    private final RevenueCalculationMapper mapper;

    // Instance data
    private final Map<String, IncomeType> incomeTypeMap;

    public AuthorRevenueCalculationService(
            AuthorRevenueRepository authorRevenueRepository,
            CollectionHelper collectionHelper,
            RevenueCalculationMapper mapper
    ) {
        this.repository = authorRevenueRepository;
        this.mapper = mapper;
        this.incomeTypeMap = collectionHelper.convertListIntoMap(Arrays.stream(IncomeType.values()).toList(), IncomeType::name);
    }

    public AuthorRevenueSummary computeAuthorRevenueSummaryForDashboard(Long authorId, AuthorRevenueSummaryRequest request) {
        if (authorId == null || authorId <= 0 || request == null || request.incomeType() == null)
            throw new InvalidDataException("Invalid data to compute Author Revenue Dashboard");

        List<AuthorRevenueProjector> authorRevenueProjectors = getTotalAuthorRevenue(authorId, request.startDate(), request.endDate(), request.incomeType());
        List<BookQuantityProjector> bookQuantityProjectors = getTotalAuthorRevenueBookCount(authorId, request.startDate(), request.endDate(), request.incomeType());

        List<AuthorIncomeTypeBasedRevenue> revenues = new ArrayList<>();

        BigDecimal overallAuthorRevenue = BigDecimal.ZERO;
        BigDecimal overallAuthorRevenueClaimed = BigDecimal.ZERO;
        BigDecimal overallAuthorRevenueNotClaimed = BigDecimal.ZERO;

        for (AuthorRevenueProjector projector : authorRevenueProjectors) {
            if (!incomeTypeMap.containsKey(projector.getIncomeType()))
                continue;

            overallAuthorRevenue = overallAuthorRevenue.add(projector.getTotalRevenue());
            overallAuthorRevenueClaimed = overallAuthorRevenueClaimed.add(projector.getRevenueClaimed());
            overallAuthorRevenueNotClaimed = overallAuthorRevenueNotClaimed.add(projector.getRevenueNotClaimed());

            revenues.add(
                    mapper.mapToAuthorIncomeTypeBasedRevenue(
                            incomeTypeMap.get(projector.getIncomeType()),
                            projector
                    )
            );
        }

        long overAllBooksBorrowed= 0;
        long overAllBooksPurchase = 0;

        for (BookQuantityProjector bookQuantityProjector : bookQuantityProjectors) {
            overAllBooksBorrowed += bookQuantityProjector.getBorrowBookCount();
            overAllBooksPurchase += bookQuantityProjector.getPurchaseBookCount();
        }

        return new AuthorRevenueSummary(
                request.startDate(),
                request.endDate(),
                overAllBooksBorrowed,
                overAllBooksPurchase,
                overallAuthorRevenue,
                overallAuthorRevenueClaimed,
                overallAuthorRevenueNotClaimed,
                revenues
        );
    }

    public List<AuthorRevenueProjector> getTotalAuthorRevenue(long authorId, LocalDateTime startDate, LocalDateTime endDate, IncomeType incomeType) {
        if (incomeType == null)
            throw new InvalidDataException("Income Type is invalid");

        return repository.getTotalAuthorRevenue(
                startDate,
                endDate,
                incomeType == IncomeType.UNKNOWN,
                incomeType == IncomeType.UNKNOWN ? null : incomeType.name(),
                true,
                authorId
        );
    }

    public List<BookQuantityProjector> getTotalAuthorRevenueBookCount(long authorId, LocalDateTime startDate, LocalDateTime endDate, IncomeType incomeType) {
        if (incomeType == null)
            throw new InvalidDataException("Income Type is invalid");

        return repository.getAllAuthorRevenueBooksQuantity(
                startDate,
                endDate,
                incomeType == IncomeType.UNKNOWN,
                incomeType == IncomeType.UNKNOWN ? null : incomeType.name(),
                true,
                authorId
        );
    }
}
