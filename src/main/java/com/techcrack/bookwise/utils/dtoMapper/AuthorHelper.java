package com.techcrack.bookwise.utils.dtoMapper;

import com.techcrack.bookwise.constans.Status;
import com.techcrack.bookwise.dtos.AuthorRequestDTO;
import com.techcrack.bookwise.dtos.AuthorResponseDTO;
import com.techcrack.bookwise.entity.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorHelper {
    private final UserHelper userHelper;

    public AuthorHelper(UserHelper userHelper) {
        this.userHelper = userHelper;
    }

    public Author mapToAuthor(AuthorRequestDTO authorRequestDTO) {
        Author author = new Author();

        author.setBio(authorRequestDTO.getBio());
        author.setUser(
                userHelper.mapToUser(
                authorRequestDTO.getUserRegisterDTO()
                )
        );

        author.setStatus(Status.PENDING);

        return author;
    }

    public AuthorResponseDTO mapToAuthorResponseDTO(Author author) {
        return new AuthorResponseDTO(
                author.getId(),
                author.getBio(),
                userHelper.mapToUserResponse(
                        author.getUser(),
                        null
                ),
                author.getStatus()
        );
    }

}
