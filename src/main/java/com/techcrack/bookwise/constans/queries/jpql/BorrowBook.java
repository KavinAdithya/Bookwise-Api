package com.techcrack.bookwise.constans.queries.jpql;

public class BorrowBook {
    public static final String FIND_ALL_BORROW_BOOKS_USER = """
                SELECT
                    new com.techcrack.bookwise.dtos.borrowbook.response.BorrowBookViewResponse(
                        br.id,
                        b.id,
                        b.coverImageUrl,
                        b.title,
                        u.name,
                        br.quantity,
                        br.borrowDate,
                        br.dueDate,
                        br.returnDate,
                        br.status,
                        br.totalAmountPaidOnReturn
                    )
                FROM BorrowBook br
                JOIN br.book b
                JOIN b.author a
                JOIN a.user u
                where br.user.id = :userId
            """;
    public static final String GET_BORROW_BOOK_DETAIL_VIEW = """
                SELECT
                    new com.techcrack.bookwise.dtos.borrowbook.response.BorrowBookDetailView(
                        br.id,
                        new com.techcrack.bookwise.dtos.borrowbook.response.BookBasicInfo(
                            b.id,
                            b.title,
                            b.description,
                            c.name,
                            u.name,
                            b.coverImageUrl
                        ),
                        br.quantity,
                        br.borrowDate,
                        br.dueDate,
                        br.returnDate,
                        br.status,
                        br.totalAmountPaidOnReturn
                    )
                FROM BorrowBook br
                JOIN br.book b
                JOIN b.category c
                JOIN b.author a
                JOIN a.user u
                WHERE br.Id = :borrowBookId
            """;
}
