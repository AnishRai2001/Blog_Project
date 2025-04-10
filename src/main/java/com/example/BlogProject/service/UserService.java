package com.example.BlogProject.service;

import java.util.List;

import com.example.BlogProject.Dto.UserDto;
import com.example.BlogProject.Entity.User;

public interface UserService {
	
	User createUser(User user);
	User updateUser(User user, Integer userId);
	User getUserById(Integer userId);
	List<User> getAllUsers();
	void deleteUser(Integer userId);
	
	
	
	
	
//	UserDto createUser(UserDto user);
//	UserDto updateUser(UserDto user,Integer userId);
//	UserDto getUserById(Integer UserId);
//	List<UserDto>getAllUser();
//	void deleteUser(Integer userId);

}
