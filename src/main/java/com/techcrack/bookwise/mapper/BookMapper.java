package com.techcrack.bookwise.mapper;

import com.techcrack.bookwise.dtos.book.response.*;
import com.techcrack.bookwise.entity.Book;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookMapper {
    public BookRegisterResponse mapToBookRegisterResponse(Book src) {
        return new BookRegisterResponse(src);
    }

    public List<UserBookViewResponse> mapToViewBookResponses(List<Book> books) {
        List<UserBookViewResponse> bookResponse = new ArrayList<>();

        for (Book book : books) {
            bookResponse.add(mapToViewBookResponse(book));
        }

        return bookResponse;
    }

    public PendingBookResponse mapToPendingBookResponse(Book book) {
        return new PendingBookResponse(book);
    }

    public List<PendingBookResponse> mapToPendingBookResponses(List<Book> books) {
        List<PendingBookResponse> response = new ArrayList<>();

        for (Book book : books) {
            response.add(mapToPendingBookResponse(book));
        }

        return response;
    }

    public UserBookViewResponse mapToViewBookResponse(Book source) {
        return new UserBookViewResponse(
                source.getId(),
                source.getTitle(),
                source.getAuthor().getUser().getName(),
                source.getCategory().getName(),
                source.getAvailableCopies(),
                source.getPurchasePrice(),
                source.getBorrowFee(),
                source.getCoverImageUrl()
        );
    }

    public AuthorBookDetailViewResponse mapToAuthorBookDetailViewResponse(Book book) {
        return new AuthorBookDetailViewResponse(book);
    }

    public AdminViewBookDetailResponse mapToAdminViewBookDetailResponse(Book source) {
        return new AdminViewBookDetailResponse(
                source.getId(),
                source.getTitle(),
                source.getISBN(),
                source.getDescription(),
                source.getAuthor().getUser().getName(),
                source.getCategory().getName(),
                source.getTotalCopies(),
                source.getAvailableCopies(),
                source.getBorrowFee(),
                source.getPurchasePrice(),
                source.getBookStatus(),
                source.getCoverImageUrl()
        );
    }
}
