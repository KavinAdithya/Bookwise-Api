package com.techcrack.bookwise.dtoMapper;

import com.techcrack.bookwise.constans.ApplicationData;
import com.techcrack.bookwise.dtos.CategoryRequestDTO;
import com.techcrack.bookwise.dtos.CategoryResponseDTO;
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
