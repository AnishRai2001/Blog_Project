package com.example.BlogProject.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BlogProject.Entity.Category;
import com.example.BlogProject.Repository.CategoryRepo;
import com.example.BlogProject.exception.CategoryNotFoundException;
import com.example.BlogProject.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    // Create a category
    @Override
    public Category createCategory(Category category) {
        return categoryRepo.save(category);
    }

    // Get a category by ID
    @Override
    public Category getCategoryById(Integer categoryId) {
        Optional<Category> category = categoryRepo.findById(categoryId);
        if (category.isPresent()) {
            return category.get();
        } else {
            throw new CategoryNotFoundException("Category not found with ID: " + categoryId);
        }
    }

    // Get all categories
    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = categoryRepo.findAll();
        if (categories.isEmpty()) {
            throw new CategoryNotFoundException("No categories found.");
        }
        return categories;
    }

    // Delete a category by ID
    @Override
    public void deleteCategory(Integer categoryId) {
        Optional<Category> category = categoryRepo.findById(categoryId);
        if (category.isPresent()) {
            categoryRepo.delete(category.get());
        } else {
            throw new CategoryNotFoundException("Category not found with ID: " + categoryId);
        }
    }

    // Update a category
    @Override
    public Category updateCategory(Category category, Integer categoryId) {
        Optional<Category> existingCategory = categoryRepo.findById(categoryId);
        if (existingCategory.isPresent()) {
            Category updatedCategory = existingCategory.get();
            updatedCategory.setCategoryTitle(category.getCategoryTitle());
            updatedCategory.setCategoryDescription(category.getCategoryDescription());
            return categoryRepo.save(updatedCategory);
        } else {
            throw new CategoryNotFoundException("Category not found with ID: " + categoryId);
        }
    }
}
