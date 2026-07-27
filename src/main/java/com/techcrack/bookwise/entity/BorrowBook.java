package com.techcrack.bookwise.entity;

import com.techcrack.bookwise.constans.ApplicationData;
import com.techcrack.bookwise.constans.BorrowStatus;
import com.techcrack.bookwise.utils.BaseEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "BorrowedBooks")
public class BorrowBook extends BaseEntity {

    @ManyToOne
    private Users user;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Book book;
    private int quantity;
    @Column(nullable = false)
    private LocalDateTime borrowDate;
    @Column(nullable = false)
    private LocalDateTime dueDate;
    private LocalDateTime returnDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private BorrowStatus status;
    private double totalAmountPaidOnReturn;


    public BorrowBook() {
        super();
    }

    public BorrowBook(Book book, LocalDateTime borrowDate, LocalDateTime dueDate, int quantity,  Users user) {
        this.book = book;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.quantity = quantity;
        this.user = user;
        this.status = BorrowStatus.PROCESSING;
        this.createdAt = ApplicationData.SYSTEM_DATE;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    public BorrowStatus getStatus() {
        return status;
    }

    public void setStatus(BorrowStatus status) {
        this.status = status;
    }

    public double getTotalAmountPaidOnReturn() {
        return totalAmountPaidOnReturn;
    }

    public void setTotalAmountPaidOnReturn(double totalAmountPaidOnReturn) {
        this.totalAmountPaidOnReturn = totalAmountPaidOnReturn;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BorrowBook that)) return false;
        return getQuantity() == that.getQuantity() && Objects.equals(getId(), that.getId()) && Objects.equals(getUser(), that.getUser()) && Objects.equals(getBook(), that.getBook()) && Objects.equals(getBorrowDate(), that.getBorrowDate()) && Objects.equals(getDueDate(), that.getDueDate()) && Objects.equals(getReturnDate(), that.getReturnDate()) && getStatus() == that.getStatus();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUser(), getBook(), getQuantity(), getBorrowDate(), getDueDate(), getReturnDate(), getStatus());
    }

    @Override
    public String toString() {
        return "BorrowBook{" +
                "book=" + book +
                ", borrowDate=" + borrowDate +
                ", dueDate=" + dueDate +
                ", quantity=" + quantity +
                ", returnDate=" + returnDate +
                ", status=" + status +
                ", totalAmountPaidOnReturn=" + totalAmountPaidOnReturn +
                ", user=" + user +
                '}';
    }
}