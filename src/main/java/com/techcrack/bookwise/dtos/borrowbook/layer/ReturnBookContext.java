package com.techcrack.bookwise.dtos.borrowbook.layer;

/**
 * Consists Data for Returning a book
 * @param userId Current User logged in
 * @param borrowBookId Borrow Book id
 * @param amountPaying Amount Paying against the due amount
 */
public record ReturnBookContext(long borrowBookId,  long userId, double amountPaying) {
}
