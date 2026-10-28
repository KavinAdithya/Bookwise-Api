package com.techcrack.bookwise.applicationServices.borrowbook.dto.request;

import com.techcrack.bookwise.applicationServices.borrowbook.dto.layer.ReturnBookContext;

public record ReturnBookRequest(long borrowBookId, double amount) {
    public ReturnBookContext buildContext(long userId) {
        return new ReturnBookContext(borrowBookId, amount);
    }
}
