package com.techcrack.bookwise.entity;

import com.techcrack.bookwise.constans.ApplicationData;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name="PurchasedBooks")
public class PurchaseBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Users user;
    @ManyToOne
    private Book book;
    private int quantity;
    private LocalDateTime purchaseDate;
    private LocalDateTime createdAt;
    private double totalAmount;

    public PurchaseBook() {
        super();
    }

    public PurchaseBook(Book book, LocalDateTime purchaseDate, int quantity, double totalAmount, Users user) {
        this.book = book;
        this.purchaseDate = purchaseDate;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
        this.user = user;
        this.createdAt = ApplicationData.SYSTEM_DATE;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
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

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
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
        if (!(o instanceof PurchaseBook that)) return false;
        return getQuantity() == that.getQuantity() && Objects.equals(getId(), that.getId()) && Objects.equals(getUser(), that.getUser()) && Objects.equals(getBook(), that.getBook()) && Objects.equals(getPurchaseDate(), that.getPurchaseDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUser(), getBook(), getQuantity(), getPurchaseDate());
    }

    @Override
    public String toString() {
        return "PurchaseBook{" +
                "user=" + user +
                ", quantity=" + quantity +
                ", totalAmount=" + totalAmount +
                ", purchaseDate=" + purchaseDate +
                ", book=" + book +
                '}';
    }
}

