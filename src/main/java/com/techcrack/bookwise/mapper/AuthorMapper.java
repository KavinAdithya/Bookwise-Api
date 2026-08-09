package com.techcrack.bookwise.mapper;

import com.techcrack.bookwise.dtos.author.request.AuthorRegisterRequest;
import com.techcrack.bookwise.dtos.author.response.AuthorRegisterResponse;
import com.techcrack.bookwise.dtos.author.response.PendingAuthorResponse;
import com.techcrack.bookwise.entity.Author;
import com.techcrack.bookwise.entity.Subscription;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AuthorMapper {

    public Author mapToAuthor(AuthorRegisterRequest authorRegisterRequest) {
        return authorRegisterRequest.buildAuthor();
    }

    public AuthorRegisterResponse mapToAuthorRegisterResponse(Author author, Subscription subscription) {
        return new AuthorRegisterResponse(author, subscription);
    }

    private PendingAuthorResponse mapToPendingAuthorResponse(Author author) {
        return new PendingAuthorResponse(author);
    }

    public List<PendingAuthorResponse> mapToPendingAuthorResponses(List<Author> authors) {
        List<PendingAuthorResponse> response = new ArrayList<>();

        for (Author author : authors) {
            response.add(mapToPendingAuthorResponse(author));
        }

        return response;
    }
}
