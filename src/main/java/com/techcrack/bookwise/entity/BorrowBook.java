package com.techcrack.bookwise.entity;

import com.techcrack.bookwise.constans.ApplicationData;
import com.techcrack.bookwise.constans.BorrowStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "BorrowedBooks")
public class BorrowBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Users user;
    @ManyToOne
    private Book book;
    private int quantity;
    private LocalDateTime borrowDate;
    private LocalDateTime dueDate;
    private LocalDateTime returnDate;
    private int freeReturnDays;
    private BorrowStatus status;
    private double totalAmountPaidOnReturn;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @ManyToOne
    private Users updateBy;

    public BorrowBook() {
        super();
    }

    public BorrowBook(Book book, LocalDateTime borrowDate, LocalDateTime dueDate, int freeReturnDays, int quantity,  Users user) {
        this.book = book;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.freeReturnDays = freeReturnDays;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public int getFreeReturnDays() {
        return freeReturnDays;
    }

    public void setFreeReturnDays(int freeReturnDays) {
        this.freeReturnDays = freeReturnDays;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Users getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Users updateBy) {
        this.updateBy = updateBy;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
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
                ", freeReturnDays=" + freeReturnDays +
                ", quantity=" + quantity +
                ", returnDate=" + returnDate +
                ", status=" + status +
                ", totalAmountPaidOnReturn=" + totalAmountPaidOnReturn +
                ", user=" + user +
                '}';
    }
}

//TotalAmountPaidOnReturn DECIMAL
//CreatedAt TIMESTAMP
//UpdatedAt timestamp
//UpdatedBy INT