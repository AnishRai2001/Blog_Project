package com.example.BlogProject.controller;

import com.example.BlogProject.Entity.Comment;
import com.example.BlogProject.Entity.Post;
import com.example.BlogProject.Repository.PostRepo;
import com.example.BlogProject.service.CommentService;
import com.example.BlogProject.exception.ResourceNotFoundException;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private PostRepo postRepo; // Use PostRepo directly to fetch Post by ID

    // Method to create a comment for a post
    @PostMapping("/postId/{postid}")
    public ResponseEntity<Map<String, Object>> createComment(@RequestBody Comment comment, @PathVariable int postid) {
        // Fetch the Post by postid
        Post post = postRepo.findById(postid).orElseThrow(() -> 
            new ResourceNotFoundException("Post not found with ID: " + postid)
        );

        // Validate comment content
        if (comment.getContent() == null || comment.getContent().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);  // Return bad request if no content
        }

        comment.setPost(post); // Set the post to the comment

        
        Comment createdComment = commentService.createComment(comment, postid);// Save the comment

        // Include post details in the response (with category, user, and comments)
        Post fullPost = createdComment.getPost(); // Get the full Post object (with Category and User)

        // Prepare a custom response with the Comment and the full Post details
        Map<String, Object> response = new HashMap<>();
        response.put("id", createdComment.getId());
        response.put("content", createdComment.getContent());
        response.put("post", fullPost);

        // Return the response with a 201 status (created)
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}