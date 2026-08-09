package com.techcrack.bookwise.mapper;

import com.techcrack.bookwise.constans.ApplicationData;
import com.techcrack.bookwise.dtos.borrowbook.layer.BorrowBookContext;
import com.techcrack.bookwise.dtos.borrowbook.layer.ReturnBookContext;
import com.techcrack.bookwise.dtos.borrowbook.request.BorrowBookRequest;
import com.techcrack.bookwise.dtos.borrowbook.request.ReturnBookRequest;
import com.techcrack.bookwise.dtos.borrowbook.response.BorrowBookResponseDTO;
import com.techcrack.bookwise.dtos.borrowbook.response.DueAmountResponse;
import com.techcrack.bookwise.dtos.borrowbook.response.ReturnBookResponse;
import com.techcrack.bookwise.entity.Book;
import com.techcrack.bookwise.entity.BorrowBook;
import com.techcrack.bookwise.entity.Users;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BorrowBookHelper {
    public BorrowBook mapToBorrowBook(BorrowBookRequest borrowBookRequestDTO) {
        BorrowBook borrowBook = borrowBookRequestDTO.buildBorrowBook();

        borrowBook.initialize(ApplicationData.HARD_CODED_CURRENT_ID);

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

    public BorrowBookContext mapToBorrowBookContext(long borrowBookId) {
        return new BorrowBookContext(borrowBookId, ApplicationData.HARD_CODED_CURRENT_ID);
    }

    public List<BorrowBookResponseDTO> mapToBorrowBookResponseDTOs(List<BorrowBook> borrowBooks) {
        List<BorrowBookResponseDTO> borrowBookResponseDTOS = new ArrayList<>();

        for (BorrowBook borrowBook : borrowBooks) {
            borrowBookResponseDTOS.add(mapToBorrowBookResponseDTO(borrowBook));
        }

        return borrowBookResponseDTOS;
    }

    public DueAmountResponse mapToDueAmountResponse(double dueAmount) {
        return new DueAmountResponse(dueAmount);
    }

    public ReturnBookContext mapToReturnBookContext(ReturnBookRequest request) {
        return request.buildContext(
                ApplicationData.HARD_CODED_CURRENT_ID
        );
    }

    public ReturnBookResponse mapToReturnBookResponse(String message) {
        return new ReturnBookResponse(message);
    }
}
