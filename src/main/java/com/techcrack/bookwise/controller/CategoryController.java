package com.techcrack.bookwise.controller;

import com.techcrack.bookwise.abstractions.CategoryService;
import com.techcrack.bookwise.mapper.CategoryMapper;
import com.techcrack.bookwise.dtos.category.request.CategoryRegisterRequest;
import com.techcrack.bookwise.dtos.category.response.CategoryRegisterResponse;
import com.techcrack.bookwise.entity.Category;
import com.techcrack.bookwise.abstractions.CurrentUserService;
import com.techcrack.bookwise.responseHelper.ApiResponseEntity;
import com.techcrack.bookwise.responseHelper.ResponseEntityHelper;
import com.techcrack.bookwise.utils.AbstractController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController extends AbstractController<CategoryController, CategoryService, CategoryMapper> {

    public CategoryController(CategoryService service, CategoryMapper helper, CurrentUserService userSession) {
       super(CategoryController.class, service, helper, userSession);
    }

    @PostMapping("/admin/category/register")
    public ResponseEntity<ApiResponseEntity<CategoryRegisterResponse>> register(@RequestBody CategoryRegisterRequest categoryRegisterRequest) {
        logger.info("Request Received for category register with {}", categoryRegisterRequest.getName());

        Category category = mapper.mapToCategory(categoryRegisterRequest);

        category = service.register(category);

        logger.info("Request for category registered completed with {}", category.getName());

        CategoryRegisterResponse response = mapper.mapToCategoryRegisterResponse(category);

        return ResponseEntityHelper.buildSuccessResponse("Category Registered Successfully", response);
    }
}
