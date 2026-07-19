package com.techcrack.bookwise.dtoMapper;

import com.techcrack.bookwise.constans.Roles;
import com.techcrack.bookwise.constans.Status;
import com.techcrack.bookwise.dtos.AuthorRegisterDTO;
import com.techcrack.bookwise.dtos.AuthorResponseDTO;
import com.techcrack.bookwise.dtos.PendingAuthorDTO;
import com.techcrack.bookwise.dtos.SubscriptionResponseDTO;
import com.techcrack.bookwise.entity.Author;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AuthorHelper {
    private final UserHelper userHelper;

    public AuthorHelper(UserHelper userHelper) {
        this.userHelper = userHelper;
    }

    public Author mapToAuthor(AuthorRegisterDTO authorRegisterDTO) {
        Author author = new Author();

        author.setBio(authorRegisterDTO.getBio());
        author.setUser(
                userHelper.mapToUser(
                    authorRegisterDTO.getUser(),
                    Roles.AUTHOR
                )
        );

        author.initialize();

        author.setStatus(Status.PENDING);

        return author;
    }

    public AuthorResponseDTO mapToAuthorResponseDTO(Author author, SubscriptionResponseDTO subscription) {
        return new AuthorResponseDTO(
                author.getId(),
                author.getBio(),
                userHelper.mapToUserResponse(
                        author.getUser(),
                        subscription
                ),
                author.getStatus()
        );
    }

    private PendingAuthorDTO mapToPendingAuthorDTO(Author author) {
        return new PendingAuthorDTO(
                author.getId(),
                author.getUser().getName(),
                author.getBio(),
                author.getUser().getUsername(),
                author.getUser().getEmail()
        );
    }

    public List<PendingAuthorDTO> mapToPendingAuthorDTOs(List<Author> authors) {
        List<PendingAuthorDTO> response = new ArrayList<>();

        for (Author author : authors) {
            response.add(mapToPendingAuthorDTO(author));
        }

        return response;
    }
}
