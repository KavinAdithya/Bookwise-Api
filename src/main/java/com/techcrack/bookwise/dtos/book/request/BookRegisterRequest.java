package com.techcrack.bookwise.dtos.book.request;

import com.techcrack.bookwise.entity.Book;

public class BookRegisterRequest {

    private String title;
    private String ISBN;
    private String description;
    private String categoryName;
    private String language;
    private int totalCopies;
    private int availableCopies;
    private double purchasePrice;
    private double borrowFee;

    public BookRegisterRequest() {
        super();
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


    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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

    public Book buildBook() {
        Book book = new Book();

        book.setTitle(title);
        book.setISBN(ISBN);
        book.setDescription(description);
        book.setLanguage(language);
        book.setTotalCopies(totalCopies);
        book.setAvailableCopies(availableCopies);
        book.setPurchasePrice(purchasePrice);
        book.setBorrowFee(borrowFee);

        return book;
    }


    @Override
    public String toString() {
        return "BookRegisterDTO{" +
                "title='" + title + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", description='" + description + '\'' +
                ", categoryName=" + categoryName +
                ", language='" + language + '\'' +
                ", totalCopies=" + totalCopies +
                ", availableCopies=" + availableCopies +
                ", purchasePrice=" + purchasePrice +
                ", borrowFee=" + borrowFee +
                '}';
    }
}
