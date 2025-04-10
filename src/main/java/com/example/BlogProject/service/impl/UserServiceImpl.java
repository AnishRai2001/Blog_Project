package com.example.BlogProject.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BlogProject.Entity.User;
import com.example.BlogProject.Repository.UserRepository;
import com.example.BlogProject.exception.UserNotFoundException;
import com.example.BlogProject.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
   
    
    @Override
    public User createUser(User user) {
        // Save user to the database
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        // Retrieve all users
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new UserNotFoundException("No users found.");
        }
        return users;
    }
    // Find the user by ID
    public User getUserById(Integer userId) {
      
        Optional<User> user = userRepository.findById(userId);
        
        if (user.isPresent()) {
            return user.get();
        } else {
            // Handle user not found case (optional)
            throw new RuntimeException("User not found with id: " + userId);
        }
    }

    @Override
    public User updateUser(User user, Integer userId) {
        // Find the user by ID
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            
            // Update the user's information
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            existingUser.setAbout(user.getAbout());
            existingUser.setPassword(user.getPassword());
            
            // Save the updated user
            return userRepository.save(existingUser);
        } else {
            // Handle user not found case (optional)
            throw new RuntimeException("User not found with id: " + userId);
        }
    }
    @Override
    public void deleteUser(Integer userId) {
        // Find user by ID
        Optional<User> optionalUser = userRepository.findById(userId);
        
        if (optionalUser.isPresent()) {
            // Delete user from the database
            userRepository.delete(optionalUser.get());
        } else {
            // Handle user not found case (optional)
            throw new RuntimeException("User not found with id: " + userId);
        }
    }


}
