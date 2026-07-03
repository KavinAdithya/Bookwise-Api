package com.techcrack.bookwise.entity;

import com.techcrack.bookwise.constans.ApplicationData;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "AuthorRevenues")
public class AuthorRevenue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Author author;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Book book;
    @ManyToOne
    @JoinColumn(nullable = false)
    private PurchaseBook purchaseBook;
    @ManyToOne
    @JoinColumn(nullable = false)
    private BorrowBook borrowBook;
    private double amount;
    @Column(nullable = false)
    private LocalDateTime createdAt;

    public AuthorRevenue() {
        super();
    }

    public AuthorRevenue(double amount, PurchaseBook purchaseBook, BorrowBook borrowBook, Book book, Author author) {
        this.amount = amount;
        this.purchaseBook = purchaseBook;
        this.borrowBook = borrowBook;
        this.book = book;
        this.author = author;
        this.createdAt = ApplicationData.SYSTEM_DATE;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public BorrowBook getBorrowBook() {
        return borrowBook;
    }

    public void setBorrowBook(BorrowBook borrowBook) {
        this.borrowBook = borrowBook;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PurchaseBook getPurchaseBook() {
        return purchaseBook;
    }

    public void setPurchaseBook(PurchaseBook purchaseBook) {
        this.purchaseBook = purchaseBook;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AuthorRevenue that)) return false;
        return Double.compare(getAmount(), that.getAmount()) == 0 && Objects.equals(getId(), that.getId()) && Objects.equals(getAuthor(), that.getAuthor()) && Objects.equals(getBook(), that.getBook()) && Objects.equals(getPurchaseBook(), that.getPurchaseBook()) && Objects.equals(getBorrowBook(), that.getBorrowBook());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAuthor(), getBook(), getPurchaseBook(), getBorrowBook(), getAmount());
    }

    @Override
    public String toString() {
        return "AuthorRevenue{" +
                "purchaseBook=" + purchaseBook +
                ", borrowBook=" + borrowBook +
                ", book=" + book +
                ", author=" + author +
                ", amount=" + amount +
                '}';
    }
}

//Id INT
//AuthorId INT
//BookId INT
//BorrowId INT
//PurchaseId INT
//Amount DECIMAL(18,2)
//CreatedAt TIMESTAM
