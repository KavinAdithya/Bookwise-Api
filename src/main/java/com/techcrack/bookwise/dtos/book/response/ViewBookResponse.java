package com.techcrack.bookwise.dtos.book.response;

import com.techcrack.bookwise.entity.Book;

import javax.swing.text.View;
import java.time.LocalDateTime;

public class ViewBookResponse {
    private Long id;
    private String title;
    private String ISBN;
    private String authorName;
    private String description;
    private String categoryName;
    private String language;
    private int availableCopies;
    private double purchasePrice;
    private double borrowFee;
    private String coverImageUrl;

    public ViewBookResponse() {
        super();
    }

    public ViewBookResponse(Book book){
        this.id = book.getId();
        this.title = book.getTitle();
        this.ISBN = book.getISBN();
        this.authorName = book.getAuthor().getUser().getName();
        this.description = book.getDescription();
        this.categoryName = book.getCategory().getName();
        this.language = book.getLanguage();
        this.availableCopies = book.getAvailableCopies();
        this.purchasePrice = book.getPurchasePrice();
        this.borrowFee = book.getBorrowFee();
        this.coverImageUrl = book.getCoverImageUrl();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
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

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }

    @Override
    public String toString() {
        return "ViewBookResponse{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", authorName='" + authorName + '\'' +
                ", description='" + description + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", language='" + language + '\'' +
                ", availableCopies=" + availableCopies +
                ", purchasePrice=" + purchasePrice +
                ", borrowFee=" + borrowFee +
                ", coverImageUrl='" + coverImageUrl + '\'' +
                '}';
    }
}
