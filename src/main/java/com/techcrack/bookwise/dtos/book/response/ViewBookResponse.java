package com.techcrack.bookwise.dtos.book.response;

import com.techcrack.bookwise.entity.Book;

import javax.swing.text.View;
import java.time.LocalDateTime;

public record ViewBookResponse (
     Long id,
     String title,
     String authorName,
     String categoryName,
     int availableCopies,
     double purchasePrice,
     double borrowFee,
     String coverImageUrl) {
}