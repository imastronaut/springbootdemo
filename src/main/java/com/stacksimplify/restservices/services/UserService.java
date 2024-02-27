package com.stacksimplify.restservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserExistsException;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
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

	public User getUserById(Long id) throws UserNotFoundException {
		// TODO Auto-generated method stub
		Optional<User> user= userRepo.findById(id);
		if(user.isEmpty()) {
			throw new UserNotFoundException("User with id : "+id + " not found");
		}
		return user.get();
	}

	public User createUser(User user) throws UserExistsException{
		// TODO Auto-generated method stub
		Optional<User> existingUser = userRepo.findByUsername(user.getUsername());
		if(existingUser.isPresent()) {
			throw new UserExistsException("user with same username or ssd already exists");
		}
			
		return userRepo.save(user);
	}

	public User updateUser(Long id,User user) throws UserNotFoundException  {
		// TODO Auto-generated method stub
		Optional<User> optionalUser = userRepo.findById(id);
		if(optionalUser.isEmpty()) {
			throw new UserNotFoundException(" user by id : "+id+" is not found, unable to update");
		}
		
		user.setId(id);
		return userRepo.save(user);
	}

	public void deleteUserById(Long id) {
		// TODO Auto-generated method stub
		if(userRepo.findById(id).isPresent()) {
			userRepo.deleteById(id);
		}else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"user with id : "+id+" doesn't exist");
		}
		
	}

	public Optional<User> getUserByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepo.findByUsername(username);
	}

	
}
