package com.techcrack.bookwise.controller;

import com.techcrack.bookwise.dtoMapper.CategoryHelper;
import com.techcrack.bookwise.dtos.CategoryRequestDTO;
import com.techcrack.bookwise.dtos.CategoryResponseDTO;
import com.techcrack.bookwise.entity.Category;
import com.techcrack.bookwise.responseHelper.ApiResponseEntity;
import com.techcrack.bookwise.responseHelper.ResponseEntityHelper;
import com.techcrack.bookwise.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {

    private final CategoryService service;
    private final CategoryHelper helper;
    private final Logger logger;

    public CategoryController(CategoryService service, CategoryHelper helper) {
        this.service = service;
        this.helper = helper;
        this.logger = LoggerFactory.getLogger(CategoryController.class);
    }

    @PostMapping("/admin/category/register")
    public ResponseEntity<ApiResponseEntity<CategoryResponseDTO>> register(@RequestBody CategoryRequestDTO categoryRequestDTO) {
        logger.info("Request Received for category register with {}", categoryRequestDTO.getName());

        Category category = helper.mapToCategory(categoryRequestDTO);

        category = service.register(category);

        logger.info("Request for category registered completed with {}", category.getName());

        CategoryResponseDTO response = helper.mapToResponse(category);

        return ResponseEntityHelper.buildSuccessResponse("Category Registered Successfully", response);
    }
}
