package com.techcrack.bookwise.applicationServices.author.abstraction;

import com.techcrack.bookwise.abstractions.BasicCRUD;
import com.techcrack.bookwise.constans.enums.Status;
import com.techcrack.bookwise.applicationServices.author.dto.response.AdminViewAuthorResponse;
import com.techcrack.bookwise.applicationServices.author.entity.Author;

import java.util.List;

public interface AuthorService extends BasicCRUD<Author> {
    List<AdminViewAuthorResponse> getAuthorsBasedOnStatus(Status status);
    int approveAuthors(List<Long> authorIds);
    int rejectAuthors(List<Long> authorIds);
    boolean isAuthorValid(Author author);
    Author getAuthorByUserId(long key);
    List<Author> getAllActiveAuthors();
    List<AdminViewAuthorResponse> getAllActiveAuthorsForAdminView();
    Author getAuthorById(long id);
    long getAuthorIdByUserId(long userId);
}
