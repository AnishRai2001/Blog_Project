package com.example.BlogProject.controller;

import com.example.BlogProject.Entity.User;
import com.example.BlogProject.service.UserService;

import jakarta.validation.Valid;

import com.example.BlogProject.exception.UserNotFoundException; // Custom exception import
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // or @Controller
@RequestMapping("/blog/users")
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping("/a")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User createdUser = userService.createUser(user);
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}

	// Get all users
	@GetMapping
	public ResponseEntity<List<User>> getAllUsers() {
		try {
			List<User> users = userService.getAllUsers();
			return new ResponseEntity<>(users, HttpStatus.OK);
		} catch (UserNotFoundException e) {
			// Handle case where no users are found
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

	}

	// get user byId
	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable Integer id) {
		try {
			User user = userService.getUserById(id);
			return new ResponseEntity<>(user, HttpStatus.OK);
		} catch (UserNotFoundException e) {
			// Return a 404 if the user is not found
			return new ResponseEntity<>(null, HttpStatus.OK);

		}
	}

	// update by id
	@PutMapping("/{id}") // Use lowercase 'id' for consistency
	public ResponseEntity<User> updateUser( @Valid @PathVariable Integer id, @RequestBody User user) {
		try {
			User updatedUser = userService.updateUser(user, id);
			return new ResponseEntity<>(updatedUser, HttpStatus.OK);
		} catch (UserNotFoundException e) {
			// Return a 404 if the user is not found
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	///// Delete user by id
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
		try {
			userService.deleteUser(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (UserNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
