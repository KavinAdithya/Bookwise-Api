package com.techcrack.bookwise.dtos.book.request;


public class PendingBookDTO {
    private Long bookId;
    private String title;
    private String ISBN;
    private String authorName;
    private String categoryName;
    private double purchasePrice;
    private double borrowFee;
    private double commissionPercentage;

    public PendingBookDTO(Long bookId, String title, String ISBN, String authorName, String categoryName, double purchasePrice, double borrowFee, double commissionPercentage) {
        this.bookId = bookId;
        this.title = title;
        this.ISBN = ISBN;
        this.authorName = authorName;
        this.categoryName = categoryName;
        this.purchasePrice = purchasePrice;
        this.borrowFee = borrowFee;
        this.commissionPercentage = commissionPercentage;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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
        return "PendingBookDTO{" +
                "borrowBookId=" + bookId +
                ", title='" + title + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", authorName='" + authorName + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", purchasePrice=" + purchasePrice +
                ", borrowFee=" + borrowFee +
                ", commissionPercentage=" + commissionPercentage +
                '}';
    }
}
