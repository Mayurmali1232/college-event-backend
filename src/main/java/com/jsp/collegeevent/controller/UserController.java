package com.jsp.collegeevent.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.collegeevent.entity.User;
import com.jsp.collegeevent.service.UserService;

import jakarta.validation.Valid;


@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/users")
public class UserController {
	
	
	 @Autowired
	    private UserService service;

	    @PostMapping("/register")
	    public User register(@Valid @RequestBody User user) {
	        return service.register(user);
	    }

	    @PostMapping("/login")
	    public User login(@RequestBody User user) {
	        return service.login(user.getEmail(), user.getPassword());
	    }
	
	    @GetMapping
	    public List<User> getAllUsers() {
	        return service.getAllUsers();
	    }
	
	 // Get all users for the Admin table
	   
	    

	    // Update a user's role from the dropdown
	    @PutMapping("/{id}/role")
	    public User updateRole(@PathVariable Long id, @RequestBody Map<String, String> payload) {
	        return service.updateUserRole(id, payload.get("role"));
	    }
	
}
