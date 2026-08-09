package com.techcrack.bookwise.mapper;

import com.techcrack.bookwise.constans.ApplicationData;
import com.techcrack.bookwise.dtos.category.request.CategoryRequestDTO;
import com.techcrack.bookwise.dtos.category.response.CategoryResponseDTO;
import com.techcrack.bookwise.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryHelper {
    public Category mapToCategory(CategoryRequestDTO categoryRequestDTO) {
        Category category = new Category(categoryRequestDTO.getName());
        category.initialize(ApplicationData.HARD_CODED_CURRENT_ID);

        return category;
    }

    public CategoryResponseDTO mapToResponse(Category category) {
        return new CategoryResponseDTO(category.getId(), category.getName());
    }
}
