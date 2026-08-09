package com.techcrack.bookwise.dtos.book.response;


import com.techcrack.bookwise.entity.Book;

public class PendingBookResponse {
    private Long bookId;
    private String title;
    private String ISBN;
    private String authorName;
    private String categoryName;
    private double purchasePrice;
    private double borrowFee;
    private double commissionPercentage;

    public PendingBookResponse(Book book) {
        this.bookId = book.getId();
        this.title = book.getTitle();
        this.ISBN = book.getISBN();
        this.authorName = book.getAuthor().getUser().getName();
        this.categoryName = book.getCategory().getName();
        this.purchasePrice = book.getPurchasePrice();
        this.borrowFee = book.getBorrowFee();
        this.commissionPercentage = book.getCommissionPercentage();
    }

    public PendingBookResponse() {
        super();
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
