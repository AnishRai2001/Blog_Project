package com.example.BlogProject.service;

import com.example.BlogProject.Entity.Comment;

public interface CommentService {
	
	Comment createComment(Comment comment, int postId);
	
	void deleteComment(int commentId);
	

}
