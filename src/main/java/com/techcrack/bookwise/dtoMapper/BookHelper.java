package com.techcrack.bookwise.dtoMapper;

import com.techcrack.bookwise.dtos.BookRegisterDTO;
import com.techcrack.bookwise.dtos.BookResponseDTO;
import com.techcrack.bookwise.entity.Author;
import com.techcrack.bookwise.entity.Book;
import com.techcrack.bookwise.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class BookHelper {
    public Book mapToBook(BookRegisterDTO src) {
        Book des = new Book();
        des.initialize();

        Category category = new Category();
        category.setName(src.getCategoryName());
        des.setCategory(category);

        Author author = new Author();
        author.setId(1L);
        des.setAuthor(author);

        des.setAvailableCopies(src.getAvailableCopies());
        des.setDescription(src.getDescription());
        des.setISBN(src.getISBN());
        des.setBorrowFee(src.getBorrowFee());
        des.setLanguage(src.getLanguage());
        des.setPurchasePrice(src.getPurchasePrice());
        des.setTitle(src.getTitle());
        des.setTotalCopies(src.getTotalCopies());

        return des;
    }

    public BookResponseDTO mapToBookResponseDTO(Book src) {
        BookResponseDTO des = new BookResponseDTO();


        des.setAuthorName(src.getAuthor().getUser().getName());
        des.setCategoryName(src.getCategory().getName());

        des.setId(src.getId());
        des.setAvailableCopies(src.getAvailableCopies());
        des.setDescription(src.getDescription());
        des.setISBN(src.getISBN());
        des.setBorrowFee(src.getBorrowFee());
        des.setLanguage(src.getLanguage());
        des.setPurchasePrice(src.getPurchasePrice());
        des.setTitle(src.getTitle());
        des.setTotalCopies(src.getTotalCopies());
        des.setPublishDate(src.getPublishDate());
        des.setCommissionPercentage(src.getCommissionPercentage());

        return des;
    }
}
