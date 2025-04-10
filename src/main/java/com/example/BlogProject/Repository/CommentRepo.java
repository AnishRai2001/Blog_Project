package com.example.BlogProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.BlogProject.Entity.Comment;

public interface CommentRepo  extends JpaRepository<Comment, Integer>{

}
