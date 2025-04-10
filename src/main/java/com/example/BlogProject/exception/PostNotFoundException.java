package com.example.BlogProject.exception;

public class PostNotFoundException extends RuntimeException{
	public PostNotFoundException(String message) {
		super(message);
	}

}
