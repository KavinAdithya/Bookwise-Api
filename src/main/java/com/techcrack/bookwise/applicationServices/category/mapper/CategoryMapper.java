package com.techcrack.bookwise.applicationServices.category.mapper;

import com.techcrack.bookwise.applicationServices.jwt.abstraction.CurrentUserService;
import com.techcrack.bookwise.applicationServices.category.dto.request.CategoryRegisterRequest;
import com.techcrack.bookwise.applicationServices.category.dto.response.CategoryChooseResponse;
import com.techcrack.bookwise.applicationServices.category.dto.response.CategoryRegisterResponse;
import com.techcrack.bookwise.applicationServices.category.entity.Category;
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
