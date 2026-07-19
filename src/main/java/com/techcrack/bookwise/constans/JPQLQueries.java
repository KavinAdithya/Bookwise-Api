package com.techcrack.bookwise.constans;

public class JPQLQueries {
    public static final String UPDATE_AUTHOR_STATUS = """
                UPDATE Author a
                SET a.status = :status
                WHERE a.id IN :ids
            """;
}
