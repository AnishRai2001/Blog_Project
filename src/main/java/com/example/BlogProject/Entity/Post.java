package com.example.BlogProject.Entity;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;  // Import the annotation

import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="posts")
@Getter
@Setter
@NoArgsConstructor
public class Post {


	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int postId;
	    
	    @Column(name="post__title")
	    private String title;
	    private String context;
	    private String imageName;
	    private Date addedDate;

	    @ManyToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "category_id")
	    private Category category;

	    @ManyToOne(cascade = CascadeType.PERSIST)
	    @JoinColumn(name = "user_id")
	    private User user;

	    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	    @JsonManagedReference  // Avoid circular reference while serializing
	    private Set<Comment> comments = new HashSet<>();
	}
