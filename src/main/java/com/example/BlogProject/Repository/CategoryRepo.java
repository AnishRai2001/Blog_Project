package com.example.BlogProject.Repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.example.BlogProject.Entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
