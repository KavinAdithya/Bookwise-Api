package com.techcrack.bookwise.dtos.borrowbook.layer;

import java.time.LocalDateTime;

public record BorrowBookContext(long bookId, long userId, LocalDateTime borrowDate) {
}
