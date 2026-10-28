package com.techcrack.bookwise.constans.enums;

public enum Subscriptions {
    FREE(Integer.MAX_VALUE, 0, 0, 0, 0),
    BASE(15, 20, 3, 20, 7),
    PREMIUM(30, 100, 7, 10, 15),
    LIFETIME(Integer.MAX_VALUE, 9000, Integer.MAX_VALUE, 5, 60);


    private final int days;
    private final double rent;
    private final long booksAllowed;
    private final double delayDailyFineAmount;
    private final int borrowDaysAllowed;

    Subscriptions(int days, int rent, int booksAllowed, double delayDailyFineAmount, int borrowDaysAllowed) {
        this.days = days;
        this.rent = rent;
        this.booksAllowed = booksAllowed;
        this.delayDailyFineAmount = delayDailyFineAmount;
        this.borrowDaysAllowed = borrowDaysAllowed;
    }

    public long getBooksAllowed() {
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

    public int getBorrowDaysAllowed() {
        return borrowDaysAllowed;
    }
}
