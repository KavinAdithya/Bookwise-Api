package com.techcrack.bookwise.dtoMapper;

import com.techcrack.bookwise.constans.ApplicationData;
import com.techcrack.bookwise.dtos.CategoryRequestDTO;
import com.techcrack.bookwise.dtos.CategoryResponseDTO;
import com.techcrack.bookwise.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryHelper {
    public Category mapToCategory(CategoryRequestDTO categoryRequestDTO) {
        return new Category(categoryRequestDTO.getName(),
               1, // Hard Code
                ApplicationData.SYSTEM_DATE);
    }

    public CategoryResponseDTO mapToResponse(Category category) {
        return new CategoryResponseDTO(category.getId(), category.getName());
    }
}
