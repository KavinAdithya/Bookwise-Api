package com.techcrack.bookwise.applicationServices.category.dto.request;

import com.techcrack.bookwise.applicationServices.category.entity.Category;

public class CategoryRegisterRequest {
    private String name;

    public CategoryRegisterRequest() {
        super();
    }

    public CategoryRegisterRequest(String name) {
        this.name = name;;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category buildCategory(long userId) {
        Category category = new Category();

        category.initialize(userId);

        category.setName(name);
        return category;
    }
    @Override
    public String toString() {
        return "CategoryRequestDTO{" +
                "name='" + name + '\'' +
                '}';
    }
}
