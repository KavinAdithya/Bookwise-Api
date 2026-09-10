package com.techcrack.bookwise.abstractions;

import com.techcrack.bookwise.entity.Author;

import java.util.List;

public interface AuthorService extends BasicCRUD<Author> {
    List<Author> getPendingAuthors();
    int approveAuthors(List<Long> authorIds);
    int rejectAuthors(List<Long> authorIds);
    boolean isAuthorValid(Author author);
    Author getAuthorByUserId(long key);
}
