package com.techcrack.bookwise.constans.queries.jpql;

public class PurchaseBook {
    public static final String FIND_ALL_USER_PURCHASED_BOOK = """
                SELECT
                    new com.techcrack.bookwise.dtos.purchasebook.response.PurchaseBookViewResponse(
                        pb.id,
                        b.title,
                        c.name,
                        u.name,
                        pb.totalAmount,
                        pb.purchaseDate,
                        b.coverImageUrl
                    )
                FROM PurchaseBook pb
                JOIN pb.book b
                JOIN b.category c
                JOIN b.author a
                JOIN a.user u
                WHERE pb.isActive
                    AND pb.user.id = :userId
            """;
}
