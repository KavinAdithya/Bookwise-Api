package com.techcrack.bookwise.helper;

import com.techcrack.bookwise.constans.ApplicationData;
import com.techcrack.bookwise.dtos.borrowbook.request.BorrowBookRequestDTO;
import com.techcrack.bookwise.dtos.borrowbook.response.BorrowBookResponseDTO;
import com.techcrack.bookwise.entity.Book;
import com.techcrack.bookwise.entity.BorrowBook;
import com.techcrack.bookwise.entity.Users;
import org.springframework.stereotype.Component;

@Component
public class BorrowBookHelper {
    public BorrowBook mapToBorrowBook(BorrowBookRequestDTO borrowBookRequestDTO) {
        BorrowBook borrowBook = borrowBookRequestDTO.buildBorrowBook();

        Users users = new Users();

        users.setId(ApplicationData.HARD_CODED_CURRENT_ID);

        borrowBook.setUser(users);

        Book book = new Book();
        book.setId(borrowBookRequestDTO.getBookId());

        borrowBook.setBook(book);
        return borrowBook;
    }

    public BorrowBookResponseDTO mapToBorrowBookResponseDTO(BorrowBook borrowBook) {
        return new BorrowBookResponseDTO(borrowBook);
    }
}
