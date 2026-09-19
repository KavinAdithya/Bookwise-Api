package com.techcrack.bookwise.dtos.book.response;

import com.techcrack.bookwise.constans.enums.BookStatus;
import com.techcrack.bookwise.entity.Book;

import java.time.LocalDateTime;

public class AuthorBookDetailViewResponse {
    private String title;
    private String ISBN;
    private String description;
    private String categoryName;
    private String language;
    private LocalDateTime publishDate;
    private int totalCopies;
    private int availableCopies;
    private double purchasePrice;
    private double borrowFee;
    private double commissionPercentage;
    private BookStatus bookStatus;
    private String coverImageUrl;

    public AuthorBookDetailViewResponse() {
        super();
    }

    public AuthorBookDetailViewResponse(Book book) {
        this.title = book.getTitle();
        this.ISBN = book.getISBN();
        this.description = book.getDescription();
        this.categoryName = book.getCategory().getName();
        this.language = book.getLanguage();
        this.publishDate = book.getPublishDate();
        this.totalCopies = book.getTotalCopies();
        this.availableCopies = book.getAvailableCopies();
        this.purchasePrice = book.getPurchasePrice();
        this.borrowFee = book.getBorrowFee();
        this.bookStatus = book.getBookStatus();
        this.commissionPercentage = book.getCommissionPercentage();
        this.coverImageUrl = book.getCoverImageUrl();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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

    public int getTotalCopies() {
        return totalCopies;
    }

    public void setTotalCopies(int totalCopies) {
        this.totalCopies = totalCopies;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public double getBorrowFee() {
        return borrowFee;
    }

    public void setBorrowFee(double borrowFee) {
        this.borrowFee = borrowFee;
    }

    public double getCommissionPercentage() {
        return commissionPercentage;
    }

    public void setCommissionPercentage(double commissionPercentage) {
        this.commissionPercentage = commissionPercentage;
    }

    public BookStatus getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(BookStatus bookStatus) {
        this.bookStatus = bookStatus;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }

    @Override
    public String toString() {
        return "AuthorBookDetailViewResponse{" +
                "title='" + title + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", description='" + description + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", language='" + language + '\'' +
                ", publishDate=" + publishDate +
                ", totalCopies=" + totalCopies +
                ", availableCopies=" + availableCopies +
                ", purchasePrice=" + purchasePrice +
                ", borrowFee=" + borrowFee +
                ", commissionPercentage=" + commissionPercentage +
                ", bookStatus=" + bookStatus +
                ", coverImageUrl='" + coverImageUrl + '\'' +
                '}';
    }
}
