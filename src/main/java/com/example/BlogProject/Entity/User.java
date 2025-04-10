package com.example.BlogProject.Entity;



import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name="users")
public class User implements UserDetails {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message="Username is required")
    @Size(min=5, message="Minimum 10 characters required for username")
    private String name;

    @Email(message="Invalid email address")
    private String email;

    @NotBlank(message="About is required")
    private String about;

    @NotBlank(message="Password is required")
    @Size(min=6, message="Minimum 6 characters required for password")
    private String password;
    
    @JsonIgnore  // Ignore the posts field to prevent infinite recursion
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();
    
    //explainnnnnnnn
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
        name = "User_role", // Name of the join table
        joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), // Foreign key to User table
        inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id") // Foreign key to Role table
    )
    private Set<Role> roles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Mapping roles to GrantedAuthority
        return this.roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName())) // Mapping role to authority
                .collect(Collectors.toList()); // Collect as List
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true; 
	}
}
