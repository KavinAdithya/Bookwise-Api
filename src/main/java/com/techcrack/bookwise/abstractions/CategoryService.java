package com.techcrack.bookwise.abstractions;

import com.techcrack.bookwise.entity.Category;

import java.util.List;

public interface CategoryService extends BasicCRUD<Category> {
    List<Category> getAllCategories();
    Category getCategoryByName(String name);
}
