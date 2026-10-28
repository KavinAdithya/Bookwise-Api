package com.techcrack.bookwise.applicationServices.borrowbook.mapper;

import com.techcrack.bookwise.applicationServices.jwt.abstraction.CurrentUserService;
import com.techcrack.bookwise.applicationServices.borrowbook.dto.layer.BorrowBookContext;
import com.techcrack.bookwise.applicationServices.borrowbook.dto.layer.ReturnBookContext;
import com.techcrack.bookwise.applicationServices.borrowbook.dto.request.ReturnBookRequest;
import com.techcrack.bookwise.applicationServices.borrowbook.dto.response.BorrowBookRegisterResponse;
import com.techcrack.bookwise.applicationServices.borrowbook.dto.response.ReturnBookResponse;
import com.techcrack.bookwise.applicationServices.borrowbook.entity.BorrowBook;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BorrowBookMapper {
    private final CurrentUserService userSession;

    public BorrowBookMapper(CurrentUserService userSession) {
        this.userSession = userSession;
    }

    public BorrowBookRegisterResponse mapToBorrowBookRegisterResponse(BorrowBook borrowBook) {
        return new BorrowBookRegisterResponse(borrowBook);
    }

    public BorrowBookContext mapToBorrowBookContext(long borrowBookId) {
        return new BorrowBookContext(borrowBookId, userSession.getCurrentUserId());
    }

    public List<BorrowBookRegisterResponse> mapToBorrowBookRegisterResponses(List<BorrowBook> borrowBooks) {
        List<BorrowBookRegisterResponse> borrowBookRegisterResponses = new ArrayList<>();

        for (BorrowBook borrowBook : borrowBooks) {
            borrowBookRegisterResponses.add(mapToBorrowBookRegisterResponse(borrowBook));
        }

        return borrowBookRegisterResponses;
    }

    public ReturnBookContext mapToReturnBookContext(ReturnBookRequest request) {
        return request.buildContext(
                userSession.getCurrentUserId()
        );
    }

    public ReturnBookResponse mapToReturnBookResponse(String message) {
        return new ReturnBookResponse(message);
    }
}
