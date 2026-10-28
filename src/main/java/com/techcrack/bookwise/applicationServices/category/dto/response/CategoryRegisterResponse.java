package com.techcrack.bookwise.applicationServices.category.dto.response;


import com.techcrack.bookwise.applicationServices.category.entity.Category;

public class CategoryRegisterResponse {

    private Long id;
    private String name;

    public CategoryRegisterResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public CategoryRegisterResponse(Category category) {
        this.id = category.getId();
        this.name = category.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CategoryResponseDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
