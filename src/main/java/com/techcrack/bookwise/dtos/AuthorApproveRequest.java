package com.techcrack.bookwise.dtos;

import java.util.List;

public class AuthorApproveRequest {
    private List<Long> authorIds;

    public List<Long> getAuthorIds() {
        return authorIds;
    }

    public void setAuthorIds(List<Long> authorIds) {
        this.authorIds = authorIds;
    }

    @Override
    public String toString() {
        return "AuthorApproveRequest{" +
                "authorIds=" + authorIds +
                '}';
    }
}
