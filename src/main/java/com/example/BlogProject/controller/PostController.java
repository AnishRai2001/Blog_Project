 package com.example.BlogProject.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.BlogProject.Entity.Category;
import com.example.BlogProject.Entity.Post;
import com.example.BlogProject.Entity.User;
import com.example.BlogProject.Response.PostResponse;
import com.example.BlogProject.service.PostService;

import com.example.BlogProject.exception.ResourceNotFoundException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
@RestController // This makes the class a REST controller
@RequestMapping("/api/posts") // This sets the base path for all methods in this controller

public class PostController{
	 @Autowired
	    private PostService postService;
	 @PostMapping
	    public ResponseEntity<Post> createPost(@RequestBody Post post, 
	                                           @RequestParam int userId, 
	                                           @RequestParam int categoryId) {
	        Post createdPost = postService.createPost(post, userId, categoryId);
	        return new ResponseEntity<>(createdPost, HttpStatus.CREATED); // Return 201 status with the created post
	    }
	 
	 

    // Update an existing post
    @PutMapping("/{postId}")
    public ResponseEntity<Post> updatePost(@RequestBody Post post, @PathVariable Integer postId) {
        Post updatedPost = postService.updatePost(post, postId);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);  // Return the updated post with 200 status
    }

    // Delete a post
    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Integer postId) {
        postService.deletePost(postId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // Return 204 status for successful deletion
    }

    // Get all posts
    @GetMapping
   
    
    public ResponseEntity<PostResponse> getAllPosts(@RequestParam int pageNumber, @RequestParam(defaultValue = "10") int pageSize) {
        PostResponse postResponse = postService.getAllPosts(pageNumber-1, pageSize);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }
    
//    public ResponseEntity getAllPosts(@RequestParam int pageNumber, @RequestParam int pageSize) {
//        Page<Post> pagePost = postService.getAllPosts(pageNumber -1, pageSize); // Pass the page number and size
//        List<Post> posts = pagePost.getContent(); // Get the list of posts from the page
//
//        return new ResponseEntity<>(posts, HttpStatus.OK);  // Return the list of posts with 200 status
//    }


    // Get a post by ID
    @GetMapping("/{postId}")
    public ResponseEntity<Post> getPostById(@PathVariable Integer postId) {
        Post post = postService.getPostById(postId); 
        if (post == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Return 404 if the post is not found
        }
        return new ResponseEntity<>(post, HttpStatus.OK);  // Return the found post with 200 status
    }
    // Get posts by user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Post>> getPostsByUser(@PathVariable Integer userId) {
        User user = new User();
        user.setId(userId); // Assuming you have the User entity with userId
        List<Post> posts = postService.getPostByUser(user);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    // Get posts by category
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Post>> getPostsByCategory(@PathVariable Integer categoryId) {
        Category category = new Category();
        category.setCategoryId(categoryId); // Assuming you have the Category entity with categoryId
        List<Post> posts = postService.findByCategory(category);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
    
    }
    
