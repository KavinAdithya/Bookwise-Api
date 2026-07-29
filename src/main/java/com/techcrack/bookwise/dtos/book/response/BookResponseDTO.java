package com.techcrack.bookwise.dtos.book.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDateTime;

@JsonPropertyOrder({
        "id",
        "title",
        "ISBN",
        "authorName",
        "description",
        "categoryName",
        "language",
        "publishDate",
        "totalCopies",
        "availableCopies",
        "purchasePrice",
        "borrowFee",
        "commissionPercentage"
})
public class BookResponseDTO {

    private Long id;
    private String title;
    private String ISBN;
    private String authorName;
    private String description;
    private String categoryName;
    private String language;
    private LocalDateTime publishDate;
    private int totalCopies;
    private int availableCopies;
    private double purchasePrice;
    private double borrowFee;
    private double commissionPercentage;

    public BookResponseDTO() {
        super();
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

    @Override
    public String toString() {
        return "BookResponseDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", authorName='" + authorName + '\'' +
                ", description='" + description + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", language='" + language + '\'' +
                ", publishDate=" + publishDate +
                ", totalCopies=" + totalCopies +
                ", availableCopies=" + availableCopies +
                ", purchasePrice=" + purchasePrice +
                ", borrowFee=" + borrowFee +
                ", commissionPercentage=" + commissionPercentage +
                '}';
    }
}
