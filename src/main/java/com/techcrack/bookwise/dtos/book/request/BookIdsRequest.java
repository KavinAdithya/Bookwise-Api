package com.techcrack.bookwise.dtos.book.request;

import java.util.List;

public class BookIdsRequest {
    private List<Long> bookIds;

    public List<Long> getBookIds() {
        return bookIds;
    }

    public void setBookIds(List<Long> bookIds) {
        this.bookIds = bookIds;
    }

    @Override
    public String toString() {
        return "AuthorApproveRequest{" +
                "authorIds=" + bookIds +
                '}';
    }
}
