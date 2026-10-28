package com.techcrack.bookwise.applicationServices.author.controller;

import com.techcrack.bookwise.applicationServices.author.abstraction.AuthorRevenueService;
import com.techcrack.bookwise.applicationServices.jwt.abstraction.CurrentUserService;
import com.techcrack.bookwise.constans.enums.IncomeType;
import com.techcrack.bookwise.applicationServices.author.dto.context.AuthorRevenueSummary;
import com.techcrack.bookwise.applicationServices.author.dto.context.AuthorRevenueSummaryRequest;
import com.techcrack.bookwise.mapper.RevenueCalculationMapper;
import com.techcrack.bookwise.responseHelper.ApiResponseEntity;
import com.techcrack.bookwise.responseHelper.ResponseEntityHelper;
import com.techcrack.bookwise.utils.AbstractController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
public class AuthorRevenueController extends AbstractController<AuthorRevenueController, AuthorRevenueService, RevenueCalculationMapper> {
    public AuthorRevenueController(AuthorRevenueService authorRevenueService,
                                  RevenueCalculationMapper mapper,
                                  CurrentUserService userSession) {
       super(AuthorRevenueController.class, authorRevenueService, mapper, userSession);
    }

    @GetMapping("/author-revenue/summary")
    public ResponseEntity<ApiResponseEntity<AuthorRevenueSummary>> computeAuthorRevenueSummary(@RequestParam("startDate")LocalDateTime startDate,
                                                                                              @RequestParam("endDate") LocalDateTime endDate,
                                                                                              @RequestParam("incomeType") IncomeType incomeType) {
        logger.info("Request received to compute author revenue summary");

        AuthorRevenueSummary revenueSummary = service.computeAuthorRevenueSummary(new AuthorRevenueSummaryRequest(startDate, endDate, incomeType));

        logger.info("Request completed to compute author revenue summary");

        return ResponseEntityHelper
                .buildSuccessResponse("Author Revenue Summary Computed", revenueSummary);
    }
}