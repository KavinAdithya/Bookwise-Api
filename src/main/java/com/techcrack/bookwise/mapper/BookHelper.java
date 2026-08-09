package com.techcrack.bookwise.mapper;

import com.techcrack.bookwise.constans.ApplicationData;
import com.techcrack.bookwise.constans.enums.Status;
import com.techcrack.bookwise.dtos.book.request.BookRegisterDTO;
import com.techcrack.bookwise.dtos.book.response.BookResponseDTO;
import com.techcrack.bookwise.dtos.book.request.PendingBookDTO;
import com.techcrack.bookwise.entity.Author;
import com.techcrack.bookwise.entity.Book;
import com.techcrack.bookwise.entity.Category;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookHelper {
    public Book mapToBook(BookRegisterDTO src) {
        Book des = new Book();
        des.initialize(ApplicationData.HARD_CODED_CURRENT_ID);

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
        des.setBookStatus(Status.PENDING);

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

    public List<BookResponseDTO> mapToBookResponseDTOs(List<Book> books) {
        List<BookResponseDTO> bookResponse = new ArrayList<>();

        for (Book book : books) {
            bookResponse.add(mapToBookResponseDTO(book));
        }

        return bookResponse;
    }

    public PendingBookDTO mapToPendingBookDTO(Book book) {
        return new PendingBookDTO(
                book.getId(),
                book.getTitle(),
                book.getISBN(),
                book.getAuthor().getUser().getName(),
                book.getCategory().getName(),
                book.getPurchasePrice(),
                book.getBorrowFee(),
                book.getCommissionPercentage()
        );
    }

    public List<PendingBookDTO> mapToPendingBookDTOs(List<Book> books) {
        List<PendingBookDTO> response = new ArrayList<>();

        for (Book book : books) {
            response.add(mapToPendingBookDTO(book));
        }

        return response;
    }

}
