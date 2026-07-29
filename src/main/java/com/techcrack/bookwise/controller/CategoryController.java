package com.techcrack.bookwise.controller;

import com.techcrack.bookwise.abstractions.CategoryService;
import com.techcrack.bookwise.helper.CategoryHelper;
import com.techcrack.bookwise.dtos.category.request.CategoryRequestDTO;
import com.techcrack.bookwise.dtos.category.response.CategoryResponseDTO;
import com.techcrack.bookwise.entity.Category;
import com.techcrack.bookwise.responseHelper.ApiResponseEntity;
import com.techcrack.bookwise.responseHelper.ResponseEntityHelper;
import com.techcrack.bookwise.utils.AbstractController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController extends AbstractController<CategoryController, CategoryService, CategoryHelper> {

    public CategoryController(CategoryService service, CategoryHelper helper) {
       super(CategoryController.class, service, helper);
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
