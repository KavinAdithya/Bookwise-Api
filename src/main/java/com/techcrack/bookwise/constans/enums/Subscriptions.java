package com.techcrack.bookwise.constans.enums;

public enum Subscriptions {
    FREE(Integer.MAX_VALUE, 0, 0, 0),
    BASE(5, 0, 3, 20),
    PREMIUM(30, 100, 7, 10),
    LIFETIME(Integer.MAX_VALUE, 9000, Integer.MAX_VALUE, 0);


    private final int days;
    private final double rent;
    private final int booksAllowed;
    private final double delayDailyFineAmount;

    Subscriptions(int days, int rent, int booksAllowed, double delayDailyFineAmount) {
        this.days = days;
        this.rent = rent;
        this.booksAllowed = booksAllowed;
        this.delayDailyFineAmount = delayDailyFineAmount;
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

    public double getDelayDailyFineAmount() {
        return delayDailyFineAmount;
    }
}
