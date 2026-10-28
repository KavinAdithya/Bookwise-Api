package com.techcrack.bookwise.applicationServices.category.controller;

import com.techcrack.bookwise.applicationServices.category.abstraction.CategoryService;
import com.techcrack.bookwise.applicationServices.category.dto.response.CategoryChooseResponse;
import com.techcrack.bookwise.applicationServices.category.mapper.CategoryMapper;
import com.techcrack.bookwise.applicationServices.category.dto.request.CategoryRegisterRequest;
import com.techcrack.bookwise.applicationServices.category.dto.response.CategoryRegisterResponse;
import com.techcrack.bookwise.applicationServices.category.entity.Category;
import com.techcrack.bookwise.applicationServices.jwt.abstraction.CurrentUserService;
import com.techcrack.bookwise.responseHelper.ApiResponseEntity;
import com.techcrack.bookwise.responseHelper.ResponseEntityHelper;
import com.techcrack.bookwise.utils.AbstractController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController extends AbstractController<CategoryController, CategoryService, CategoryMapper> {

    public CategoryController(CategoryService service, CategoryMapper helper, CurrentUserService userSession) {
       super(CategoryController.class, service, helper, userSession);
    }

    @PostMapping("/categories/register")
    public ResponseEntity<ApiResponseEntity<CategoryRegisterResponse>> registerCategory(@RequestBody CategoryRegisterRequest categoryRegisterRequest) {
        logger.info("Request Received for category register with {}", categoryRegisterRequest.getName());

        Category category = mapper.mapToCategory(categoryRegisterRequest);

        category = service.register(category);

        logger.info("Request for category registered completed with {}", category.getName());

        CategoryRegisterResponse response = mapper.mapToCategoryRegisterResponse(category);

        return ResponseEntityHelper.buildSuccessResponse("Category Registered Successfully", response);
    }

    @GetMapping("/categories")
    public ResponseEntity<ApiResponseEntity<List<CategoryChooseResponse>>> getAllActiveCategories() {
        logger.info("Request Received to get all categories for book choice");

        List<Category> categories = service.getAllCategories();

        List<CategoryChooseResponse> responses = mapper.mapToCategoryChooseResponses(categories);
        return ResponseEntityHelper
                .buildSuccessResponse("Categories fetched successfully", responses);
    }
}
