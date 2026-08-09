package com.techcrack.bookwise.mapper;

import com.techcrack.bookwise.abstractions.CurrentUserService;
import com.techcrack.bookwise.constans.ApplicationData;
import com.techcrack.bookwise.dtos.borrowbook.layer.BorrowBookContext;
import com.techcrack.bookwise.dtos.borrowbook.layer.ReturnBookContext;
import com.techcrack.bookwise.dtos.borrowbook.request.ReturnBookRequest;
import com.techcrack.bookwise.dtos.borrowbook.response.BorrowBookRegisterResponse;
import com.techcrack.bookwise.dtos.borrowbook.response.DueAmountResponse;
import com.techcrack.bookwise.dtos.borrowbook.response.ReturnBookResponse;
import com.techcrack.bookwise.entity.BorrowBook;
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

    public DueAmountResponse mapToDueAmountResponse(double dueAmount) {
        return new DueAmountResponse(dueAmount);
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
