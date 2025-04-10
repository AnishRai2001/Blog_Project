package com.example.BlogProject.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.BlogProject.Entity.Category;
import com.example.BlogProject.Entity.Post;
import com.example.BlogProject.Entity.User;
import com.example.BlogProject.Repository.CategoryRepo;
import com.example.BlogProject.Repository.PostRepo;
import com.example.BlogProject.Repository.UserRepository;
import com.example.BlogProject.Response.PostResponse;
import com.example.BlogProject.exception.PostNotFoundException;
import com.example.BlogProject.service.PostService;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private CategoryRepo categoryRepo;

    
 // In your service, ensure that the category is saved before associating it with the post
    public Post createPost(Post post, int Id ,int categoryId) {
    	 User user = userRepository.findById(Id)
                 .orElseThrow(() -> new IllegalArgumentException("User not found"));
    	 post.setUser(user);
    	 
    	// Find the category by categoryId, or throw an exception if not found
         Category category = categoryRepo.findById(categoryId)
                 .orElseThrow(() -> new IllegalArgumentException("Category not found"));
         // Set the category to the post
         post.setCategory(category);

         // Save the post to the database
         return postRepo.save(post);
    	 
    }

  

    @Override
    public Post updatePost(Post post, Integer postId) {
        Optional<Post> existingPost = postRepo.findById(postId);
        if (existingPost.isPresent()) {
            Post updatedPost = existingPost.get();
            updatedPost.setTitle(post.getTitle());
            updatedPost.setContext(post.getContext());
            updatedPost.setCategory(post.getCategory());
            updatedPost.setImageName(post.getImageName());
            updatedPost.setAddedDate(post.getAddedDate());
            return postRepo.save(updatedPost);
        } else {
            throw new PostNotFoundException("Post not found with ID: " + postId);
        }
    }

    @Override
    public void deletePost(Integer postId) {
        Optional<Post> post = postRepo.findById(postId);
        if (post.isPresent()) {
            postRepo.delete(post.get());
        } else {
            throw new PostNotFoundException("Post not found with ID: " + postId); // Corrected exception type
        }
    }

    @Override
    public List<Post> getAllPosts() {
        List<Post> posts = postRepo.findAll();
        if (posts.isEmpty()) {
            throw new PostNotFoundException("No posts found");
        }
        return posts;
    }

    @Override
    public List<Post> getPostByUser(User user) {
        List<Post> posts = postRepo.findByUser(user);
        if (posts.isEmpty()) {
            throw new PostNotFoundException("No posts found for the given user");
        }
        return posts;
    }

    @Override
    public Post getPostById(Integer postId) {
        Optional<Post> post = postRepo.findById(postId);
        if (post.isPresent()) {
            return post.get();
        } else {
            throw new PostNotFoundException("No post found with ID: " + postId);
        }
    }
public List<Post> findByCategory(Category category) {
        List<Post> posts = postRepo.findByCategory(category);
        if (posts.isEmpty()) {
            throw new PostNotFoundException("No posts found for the given category"); // Corrected message
        }
        return posts;
}



@Override
public PostResponse  getAllPosts(int pageNumber, int pageSize) {
	  // Create Pageable object with the requested page and page size
    Pageable pageable = PageRequest.of(pageNumber, pageSize);
    System.out.println("Request Parameters - Page Number: " + pageNumber + ", Page Size: " + pageSize);
    // Get a paginated result from the repository
    Page<Post> postPage = postRepo.findAll(pageable);
    
    // Log the number of posts fetched
   // System.out.println("Total Posts Retrieved: " + postPage.getNumberOfElements());
    System.out.println("Total Posts in Database: " + postPage.getTotalElements());

    // Create a PostResponse object and set the values
    PostResponse postResponse = new PostResponse();
    postResponse.setContent(postPage.getContent());  // Get content (list of posts)
    postResponse.setPageNumber(postPage.getNumber());  // Current page number
    postResponse.setPageSize(postPage.getSize());  // Size of each page
    postResponse.setTotalElements((int) postPage.getTotalElements());  // Total elements (posts)
    postResponse.setTotalPages(postPage.getTotalPages());  // Total pages
    postResponse.setLastPage(postPage.isLast());  // Whether it's the last page

    return postResponse;
}



public Post findById(int postid) {
    Optional<Post> post = postRepo.findById(postid);
    
    // Return the post if found, else return null or throw an exception
    return post.orElse(null);

}
}





