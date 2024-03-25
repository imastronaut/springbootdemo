package com.stacksimplify.restservices.controllers;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.entities.Views;
import com.stacksimplify.restservices.exceptions.UserNameNotFoundException;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.services.UserService;

import jakarta.validation.constraints.Min;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@Validated
@RequestMapping("/jsonview/users")
public class UserJsonViewController {
	
	private UserService userService;

	public UserJsonViewController(UserService userService) {
		this.userService = userService;
	}

	
	
	@JsonView(Views.External.class)
	@GetMapping("/external/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") @Min(1) Long id) {
		User user = null;
		try {
			user = userService.getUserById(id);
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
		}
		
		return new ResponseEntity<>(user, HttpStatus.OK);
		
	}
	
	
	@JsonView(Views.Internal.class)
	@GetMapping("/internal/{id}")
	public ResponseEntity<User> getUserById2(@PathVariable("id") @Min(1) Long id) {
		User user = null;
		try {
			user = userService.getUserById(id);
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
		}
		
		return new ResponseEntity<>(user, HttpStatus.OK);
		
	}

	
	

	
}
