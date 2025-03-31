package com.HarvestHopper.service;

import com.HarvestHopper.model.Category;

import java.util.Optional;

public interface CategoryService {

    Category createCategory(Category category);
    Category updateCategory(Long id, Category categoryDetails);
    void deleteCategory(Long id);
    Optional<Category> getCategoryById(long id);
}
