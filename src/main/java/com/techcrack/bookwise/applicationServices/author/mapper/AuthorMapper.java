package com.techcrack.bookwise.applicationServices.author.mapper;

import com.techcrack.bookwise.applicationServices.author.dto.request.AuthorRegisterRequest;
import com.techcrack.bookwise.applicationServices.author.dto.response.AuthorRegisterResponse;
import com.techcrack.bookwise.applicationServices.author.dto.response.PendingAuthorResponse;
import com.techcrack.bookwise.applicationServices.author.dto.response.ViewAuthorResponse;
import com.techcrack.bookwise.applicationServices.author.entity.Author;
import com.techcrack.bookwise.applicationServices.subscription.entity.Subscription;
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

    public ViewAuthorResponse mapToViewAuthorResponse(Author author) {
        return new ViewAuthorResponse(author);
    }

    public List<ViewAuthorResponse> mapToViewAuthorResponses(List<Author> authors) {
        List<ViewAuthorResponse> responses = new ArrayList<>();

        for (Author author : authors) {
            responses.add(mapToViewAuthorResponse(author));
        }

        return responses;
    }
}
