package com.example.BlogProject.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.BlogProject.Entity.Category;
import com.example.BlogProject.Entity.Post;
import com.example.BlogProject.Entity.User;
import com.example.BlogProject.Response.PostResponse;

public interface PostService {

//    Post createPost(Post post);  // Method to create a post

    Post updatePost(Post post, Integer postId);  // Method to update a post--

    void deletePost(Integer postId);  // Method to delete a post--

    List<Post> getAllPosts();  // Method to get all posts--

    Post getPostById(Integer postId);  // Method to get post by ID-----

    List<Post> getPostByUser(User user);  // Method to get posts by a specific user----
    
    List<Post> findByCategory(Category category);//---

	Post createPost(Post post, int Id, int categoryId);

//	Page<Post> getAllPosts(int pageNumber,int pageSize);

	PostResponse getAllPosts(int pageNumber,int pageSize);

	Post findById(int postid);

}
