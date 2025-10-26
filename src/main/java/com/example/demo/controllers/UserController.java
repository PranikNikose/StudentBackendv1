package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.User;

@RestController
@RequestMapping("/api/users")
public class UserController {

	// Global variable to store candidates
	private List<User> Users = Collections.synchronizedList(new ArrayList<>());

	// API to add a User
	@PostMapping("/addUser")
	public String addUser(@RequestBody User User) {
		if (!Users.isEmpty()) {
			int lastId = Users.get(Users.size() - 1).getId(); // get last User ID
			User.setId(lastId + 1); // increment by 1
		} else {
			User.setId(1); // first User
		}

		Users.add(User);
		return "User added successfully!";
	}

	// API to fetch all Users
	@GetMapping("/getAllUsers")
	public List<User> getUsers() {
		return Users;
	}

	// Optional: Clear all Users
	@DeleteMapping("/clear")
	public String clearUsers() {
		Users.clear();
		return "All Users cleared!";
	}

	// API to update a User by ID
	@PutMapping("/update/{id}")
	public String updateUser(@PathVariable int id, @RequestBody User updatedUser) {
		for (User User : Users) {
			if (User.getId() == id) {
				User.setFirstName(updatedUser.getFirstName());
				User.setLastName(updatedUser.getLastName());
				User.setMobile(updatedUser.getMobile());
				User.setEmail(updatedUser.getEmail());
				return "User updated successfully!";
			}
		}
		return "User with ID " + id + " not found!";
	}
}
