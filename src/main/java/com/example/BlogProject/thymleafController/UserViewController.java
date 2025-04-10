//package com.example.BlogProject.thymleafController;
//
//import com.example.BlogProject.Entity.User;
//import com.example.BlogProject.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Controller
//@RequestMapping("/users")
//public class UserViewController {
//
//    @Autowired
//    private UserService userService;
//
//    // Display all users
//    @GetMapping
//    public String getAllUsers(Model model) {
//        List<User> users = userService.getAllUsers();
//        model.addAttribute("users", users);
//        return "userList"; // Corresponds to userList.html
//    }
//
//    // View user details
//    @GetMapping("/{id}")
//    public String getUserById(@PathVariable Integer id, Model model) {
//        User user = userService.getUserById(id);
//        model.addAttribute("user", user);
//        return "userDetail"; // Corresponds to userDetail.html
//    }
//
//    // Create new user
//    @GetMapping("/new")
//    public String showCreateUserForm(Model model) {
//        model.addAttribute("user", new User()); // Empty user object to bind to the form
//        return "createUser"; // Corresponds to createUser.html
//    }
//
//    // Create user POST request
//    @PostMapping("/a")
//    public String createUser(@ModelAttribute User user) {
//        userService.createUser(user);
//        return "redirect:/users"; // Redirect to user list after creation
//    }
//
//    // Update user
//    @GetMapping("/edit/{id}")
//    public String showUpdateUserForm(@PathVariable Integer id, Model model) {
//        User user = userService.getUserById(id);
//        model.addAttribute("user", user);
//        return "updateUser"; // Corresponds to updateUser.html
//    }
//
//    @PostMapping("/edit/{id}")
//    public String updateUser(@PathVariable Integer id, @ModelAttribute User user) {
//        userService.updateUser(user, id);
//        return "redirect:/users"; // Redirect to user list after updating
//    }
//
//    // Delete user
//    @GetMapping("/delete/{id}")
//    public String deleteUser(@PathVariable Integer id) {
//        userService.deleteUser(id);
//        return "redirect:/users"; // Redirect to user list after deletion
//    }
//}
