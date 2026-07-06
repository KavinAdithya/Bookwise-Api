package com.techcrack.bookwise.controller;

import com.techcrack.bookwise.dtos.AuthorRequestDTO;
import com.techcrack.bookwise.dtos.AuthorResponseDTO;
import com.techcrack.bookwise.entity.Author;
import com.techcrack.bookwise.service.AuthorService;
import com.techcrack.bookwise.utils.responseHelper.ApiResponseEntity;
import com.techcrack.bookwise.utils.dtoMapper.AuthorHelper;
import com.techcrack.bookwise.utils.responseHelper.ResponseEntityHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorController {

    private final AuthorService service;
    private final AuthorHelper helper;
    private final Logger logger;

    public AuthorController(AuthorService service, AuthorHelper helper) {
        this.service = service;
        this.helper = helper;
        this.logger = LoggerFactory.getLogger(AuthorController.class);
    }

    @PostMapping("/author/register")
    public ResponseEntity<ApiResponseEntity<AuthorResponseDTO>> register(AuthorRequestDTO authorRequestDTO) {
       logger.info("Request received for author registration {}", authorRequestDTO.getUserRegisterDTO().getUsername());

       Author author = helper.mapToAuthor(authorRequestDTO);
       author = service.register(author);
       AuthorResponseDTO response = helper.mapToAuthorResponseDTO(author);

       logger.info("Request completed for author registration {}", response.getUser().getUsername());
       return ResponseEntityHelper
               .buildSuccessResponse("Author Registered successfully", response);
    }
}
