package com.example.BlogProject.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BlogProject.Entity.Category;
import com.example.BlogProject.Repository.CategoryRepo;
import com.example.BlogProject.Entity.Category;
import java.util.List;

public interface CategoryService {
	

    Category createCategory(Category category);

    Category updateCategory(Category category, Integer categoryId);

    Category getCategoryById(Integer categoryId);

    List<Category> getAllCategories();

    void deleteCategory(Integer categoryId);
}
