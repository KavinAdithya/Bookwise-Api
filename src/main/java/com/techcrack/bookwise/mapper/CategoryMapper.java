package com.techcrack.bookwise.mapper;

import com.techcrack.bookwise.abstractions.CurrentUserService;
import com.techcrack.bookwise.dtos.category.request.CategoryRegisterRequest;
import com.techcrack.bookwise.dtos.category.response.CategoryChooseResponse;
import com.techcrack.bookwise.dtos.category.response.CategoryRegisterResponse;
import com.techcrack.bookwise.entity.Category;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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

    public List<CategoryChooseResponse> mapToCategoryChooseResponses(List<Category> categories) {
        List<CategoryChooseResponse> responses = new ArrayList<>();

        long idx = 0;
        for (Category category : categories) {
            responses.add(mapToCategoryChooseResponse(idx++, category));
        }

        return responses;
    }

    public CategoryChooseResponse mapToCategoryChooseResponse(long idx, Category category) {
        return new CategoryChooseResponse(idx, category.getName());
    }
}
