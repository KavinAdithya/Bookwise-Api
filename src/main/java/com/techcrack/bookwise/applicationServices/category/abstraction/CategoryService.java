package com.techcrack.bookwise.applicationServices.category.abstraction;

import com.techcrack.bookwise.abstractions.BasicCRUD;
import com.techcrack.bookwise.applicationServices.category.entity.Category;

import java.util.List;

public interface CategoryService extends BasicCRUD<Category> {
    List<Category> getAllCategories();
    Category getCategoryByName(String name);
}
