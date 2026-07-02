package com.techcrack.bookwise.constans;

public enum Subscriptions {
    FREE_PLAN(5, 0, 3),
    PREMIUM_PLAN(30, 100, 7),
    LIFETIME_PLAN(Integer.MAX_VALUE, 9000, Integer.MAX_VALUE);


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
