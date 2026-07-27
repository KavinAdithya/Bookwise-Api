package com.techcrack.bookwise.helper;

import com.techcrack.bookwise.abstractions.BorrowBookService;
import com.techcrack.bookwise.constans.ApplicationData;
import com.techcrack.bookwise.dtos.BorrowBookRequestDTO;
import com.techcrack.bookwise.dtos.BorrowBookResponseDTO;
import com.techcrack.bookwise.entity.BorrowBook;
import com.techcrack.bookwise.entity.Users;
import org.springframework.stereotype.Component;

@Component
public class BorrowBookHelper {
    public BorrowBook mapToBorrowBook(BorrowBookRequestDTO borrowBookRequestDTO) {
        BorrowBook borrowBook = borrowBookRequestDTO.buildBorrowBook();

        Users users = new Users();

        users.setId(ApplicationData.HARD_CODED_CURRENT_ID);

        return borrowBook;
    }

    public BorrowBookResponseDTO mapToBorrowBookResponseDTO(BorrowBook borrowBook) {
        return new BorrowBookResponseDTO(borrowBook);
    }
}
