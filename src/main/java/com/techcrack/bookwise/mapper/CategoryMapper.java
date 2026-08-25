package com.techcrack.bookwise.mapper;

import com.techcrack.bookwise.abstractions.CurrentUserService;
import com.techcrack.bookwise.dtos.category.request.CategoryRegisterRequest;
import com.techcrack.bookwise.dtos.category.response.CategoryRegisterResponse;
import com.techcrack.bookwise.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    private final CurrentUserService userSession;

    public CategoryMapper(CurrentUserService userSession) {
        this.userSession = userSession;
    }

    public Category mapToCategory(CategoryRegisterRequest categoryRegisterRequest) {
        return categoryRegisterRequest.buildCategory(userSession.getCurrentUserId());
    }

    public CategoryRegisterResponse mapToCategoryRegisterResponse(Category category) {
        return new CategoryRegisterResponse(category);
    }
}
