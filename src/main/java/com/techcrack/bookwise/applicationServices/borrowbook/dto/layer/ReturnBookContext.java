package com.techcrack.bookwise.applicationServices.borrowbook.dto.layer;

/**
 * Consists Data for Returning a book
 * @param borrowBookId Borrow Book id
 * @param amountPaying Amount Paying against the due amount
 */
public record ReturnBookContext(long borrowBookId,  double amountPaying) {
}
