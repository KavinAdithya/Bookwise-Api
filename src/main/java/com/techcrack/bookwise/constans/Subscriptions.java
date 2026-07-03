package com.techcrack.bookwise.constans;

public enum Subscriptions {
    FREE(5, 0, 3),
    PREMIUM(30, 100, 7),
    LIFETIME(Integer.MAX_VALUE, 9000, Integer.MAX_VALUE);


    private final int days;
    private final double rent;
    private final int booksAllowed;

    Subscriptions(int days, int rent, int booksAllowed) {
        this.days = days;
        this.rent = rent;
        this.booksAllowed = booksAllowed;
    }

    public int getBooksAllowed() {
        return booksAllowed;
    }

    public int getDays() {
        return days;
    }

    public double getRent() {
        return rent;
    }
}
