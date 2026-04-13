package com.jsp.collegeevent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsp.collegeevent.entity.User;
import com.jsp.collegeevent.repo.UserRepository;

@Service
public class UserService {

	

	@Autowired
    private UserRepository repo;
	
	public UserService() {
		
	}

    public User register(User user) {
        if (repo.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("Email already exists");
        }
        return repo.save(user);
    }

    public User login(String email, String password) {
        System.out.println("--- LOGIN ATTEMPT ---");
        System.out.println("Email typed in React: [" + email + "]");
        System.out.println("Password typed in React: [" + password + "]");

        User user = repo.findByEmail(email);
        
        if (user == null) {
            System.out.println("ERROR: No user found in database with that exact email.");
            throw new RuntimeException("Invalid credentials");
        }

        System.out.println("Password found in Database: [" + user.getPassword() + "]");

        if (!user.getPassword().equals(password)) {
            System.out.println("ERROR: Passwords do not match!");
            throw new RuntimeException("Invalid credentials");
        }

        System.out.println("SUCCESS! Logging in " + user.getName());
        return user;
    }
    
    public List<User> getAllUsers() 
    {
        return repo.findAll();
    }
    
   
    
    
   

    public User updateUserRole(Long id, String newRole) {
        User user = repo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setRole(newRole);
        return repo.save(user);
    }
    
    
}
