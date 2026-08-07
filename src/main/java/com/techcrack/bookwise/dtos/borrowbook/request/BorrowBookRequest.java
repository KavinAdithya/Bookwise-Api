package com.techcrack.bookwise.dtos.borrowbook.request;

import com.techcrack.bookwise.entity.Book;
import com.techcrack.bookwise.entity.BorrowBook;

public class BorrowBookRequest {
    private long bookId;
    private int quantity;

    public BorrowBookRequest() {
        super();
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BorrowBook buildBorrowBook() {
        BorrowBook borrowBook = new BorrowBook();

        borrowBook.setQuantity(quantity);

        return borrowBook;
    }

    @Override
    public String toString() {
        return "BorrowBookRequestDTO{" +
                "borrowBookId=" + bookId +
                ", quantity=" + quantity +
                '}';
    }
}
