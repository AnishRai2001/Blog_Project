package com.example.BlogProject.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.BlogProject.Entity.Category;
import com.example.BlogProject.Entity.Post;
import com.example.BlogProject.Entity.User;

public interface PostRepo extends JpaRepository<Post, Integer>{
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	 Page<Post> findAll(Pageable pageable);  // Pagination method
}
