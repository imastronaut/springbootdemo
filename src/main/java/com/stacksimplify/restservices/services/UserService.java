package com.stacksimplify.restservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.repositories.UserRepository;

@Service
public class UserService {
	
	private UserRepository userRepo;
	
	public UserService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}
	
	public List<User> getAllUsers(){
		return userRepo.findAll();
		
	}

	public Optional<User> getUserById(Long id) {
		// TODO Auto-generated method stub
		return userRepo.findById(id);
	}

	public User createUser(User user) {
		// TODO Auto-generated method stub
		return userRepo.save(user);
	}

	public User updateUser(User user) {
		// TODO Auto-generated method stub
		return userRepo.save(user);
	}

	public void deleteUserById(Long id) {
		// TODO Auto-generated method stub
		if(userRepo.findById(id).isPresent()) {
			userRepo.deleteById(id);
		}
		
	}

	public Optional<User> getUserByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepo.findByUsername(username);
	}

	
}
