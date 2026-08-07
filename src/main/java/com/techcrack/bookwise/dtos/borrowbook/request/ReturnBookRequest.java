package com.techcrack.bookwise.dtos.borrowbook.request;

import com.techcrack.bookwise.dtos.borrowbook.layer.ReturnBookContext;

public record ReturnBookRequest(long borrowBookId, long amount) {
    public ReturnBookContext buildContext(long userId) {
        return new ReturnBookContext(borrowBookId, amount);
    }
}
