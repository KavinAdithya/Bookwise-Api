package com.techcrack.bookwise.dtos.borrowbook.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.techcrack.bookwise.constans.enums.BorrowStatus;
import com.techcrack.bookwise.entity.BorrowBook;

import java.time.LocalDateTime;

@JsonPropertyOrder({
        "borrowBookId",
        "username",
        "bookTitle",
        "quantity",
        "status",
        "borrowDate",
        "dueDate"
})
public class BorrowBookResponseDTO {

    private long borrowBookId;
    private String username;
    private String bookTitle;
    private int quantity;
    private LocalDateTime borrowDate;
    private LocalDateTime dueDate;
    private BorrowStatus status;

    public BorrowBookResponseDTO() {
        super();
    }

    public BorrowBookResponseDTO(BorrowBook borrowBook) {
        this.username = borrowBook.getUser().getUsername();
        this.bookTitle = borrowBook.getBook().getTitle();
        this.quantity = borrowBook.getQuantity();
        this.borrowDate = borrowBook.getBorrowDate();
        this.dueDate = borrowBook.getDueDate();
        this.status = borrowBook.getStatus();
        this.borrowBookId = borrowBook.getId();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDateTime borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public BorrowStatus getStatus() {
        return status;
    }

    public void setStatus(BorrowStatus status) {
        this.status = status;
    }

    public long getBorrowBookId() {
        return borrowBookId;
    }

    public void setBorrowBookId(long borrowBookId) {
        this.borrowBookId = borrowBookId;
    }

    @Override
    public String toString() {
        return "BorrowBookResponseDTO{" +
                "borrowBookId=" + borrowBookId +
                ", username='" + username + '\'' +
                ", bookTitle='" + bookTitle + '\'' +
                ", quantity=" + quantity +
                ", borrowDate=" + borrowDate +
                ", dueDate=" + dueDate +
                ", status=" + status +
                '}';
    }
}
