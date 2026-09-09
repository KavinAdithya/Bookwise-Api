package com.techcrack.bookwise.mapper;

import com.techcrack.bookwise.dtos.book.response.BookRegisterResponse;
import com.techcrack.bookwise.dtos.book.response.PendingBookResponse;
import com.techcrack.bookwise.dtos.book.response.ViewBookResponse;
import com.techcrack.bookwise.entity.Book;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookMapper {
    public BookRegisterResponse mapToBookRegisterResponse(Book src) {
        return new BookRegisterResponse(src);
    }

    public List<ViewBookResponse> mapToViewBookResponses(List<Book> books) {
        List<ViewBookResponse> bookResponse = new ArrayList<>();

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

    public ViewBookResponse mapToViewBookResponse(Book book) {
        return new ViewBookResponse(book);
    }
}
