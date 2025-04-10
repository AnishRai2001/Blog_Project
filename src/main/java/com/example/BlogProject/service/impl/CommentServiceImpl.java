package com.example.BlogProject.service.impl;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BlogProject.Entity.Comment;
import com.example.BlogProject.Entity.Post;
import com.example.BlogProject.Repository.CommentRepo;
import com.example.BlogProject.Repository.PostRepo;
import com.example.BlogProject.exception.CategoryNotFoundException;
import com.example.BlogProject.exception.ResourceNotFoundException;
import com.example.BlogProject.service.CommentService;
@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private CommentRepo commentRepo;
	
	
	@Override
	public Comment createComment(Comment comment, int postId) {
		Optional<Post> posts=postRepo.findById(postId);
		if(posts.isPresent()) {
		Post post=posts.get();
			comment.setPost(post);
		
		 return commentRepo.save(comment);
    }
	else {
        // If the post is not found, throw an exception
        throw new ResourceNotFoundException("Post not found with ID: " + postId);
	}
    }
	

	@Override
	public void deleteComment(int commentId) {
		Optional<Comment>comments=commentRepo.findById(commentId);
		if(comments.isPresent()) {
			comments.get();
			commentRepo.delete(comments.get());
		}
		else {
			throw new ResourceNotFoundException("Comment not found with ID: " + commentId);  // Correct the error message here
		}
		
		
	}

}
