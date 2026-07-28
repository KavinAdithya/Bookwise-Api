package com.techcrack.bookwise.entity;

import com.techcrack.bookwise.constans.Status;
import com.techcrack.bookwise.utils.BaseEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "Books")
public class Book extends BaseEntity {

    @Column(unique = true)
    private String title;
    @Column(unique = true)
    private String ISBN;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Author author;
    private String description;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Category category;
    @Column(nullable = false)
    private String language;
    private LocalDateTime publishDate;
    private int totalCopies;
    private int availableCopies;
    private double purchasePrice;
    private double borrowFee;
    private double commissionPercentage;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status bookStatus;

    public Book() {
    }

    public Status getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(Status bookStatus) {
        this.bookStatus = bookStatus;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    public double getBorrowFee() {
        return borrowFee;
    }

    public void setBorrowFee(double borrowFee) {
        this.borrowFee = borrowFee;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getCommissionPercentage() {
        return commissionPercentage;
    }

    public void setCommissionPercentage(double commissionPercentage) {
        this.commissionPercentage = commissionPercentage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public LocalDateTime getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDateTime publishDate) {
        this.publishDate = publishDate;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTotalCopies() {
        return totalCopies;
    }

    public void setTotalCopies(int totalCopies) {
        this.totalCopies = totalCopies;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(long updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;
        return Objects.equals(getId(), book.getId()) && Objects.equals(getTitle(), book.getTitle()) && Objects.equals(getISBN(), book.getISBN()) && Objects.equals(getAuthor(), book.getAuthor()) && Objects.equals(getDescription(), book.getDescription()) && Objects.equals(getCategory(), book.getCategory());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getISBN(), getAuthor(), getDescription(), getCategory());
    }

    @Override
    public String toString() {
        return "Book{" +
                "updatedAt=" + updatedAt +
                ", totalCopies=" + totalCopies +
                ", title='" + title + '\'' +
                ", purchasePrice=" + purchasePrice +
                ", publishDate=" + publishDate +
                ", language='" + language + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", isActive=" + isActive +
                ", description='" + description + '\'' +
                ", commissionPercentage=" + commissionPercentage +
                ", category=" + category +
                ", borrowFee=" + borrowFee +
                ", availableCopies=" + availableCopies +
                ", BookStatus=" + bookStatus +
                ", author=" + author +
                '}';
    }
}