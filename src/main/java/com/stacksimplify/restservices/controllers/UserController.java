package com.stacksimplify.restservices.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserExistsException;
import com.stacksimplify.restservices.exceptions.UserNameNotFoundException;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.services.UserService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

@RestController
@Validated
@RequestMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
@OpenAPIDefinition(info = @Info(title = "Stacksimplify user management service", version = "1.0", description = "Your API document description"))
@Tag(name = "UserController", description = "Controller for APIs related to managing items")
public class UserController {

	@Autowired
	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@Operation(summary = "gets all users", description = "Geta list of all users")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	public List<User> getAllUsers() {;
		return userService.getAllUsers();
	}

//	@PostMapping(consumes = "application/json")
	@PostMapping(consumes = "application/xml")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		try {
			User createdUser = userService.createUser(user);
			
			return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
			
		} catch (UserExistsException e) {
			// TODO Auto-generated catch block
			throw new ResponseStatusException(HttpStatus.CONFLICT,e.getMessage());
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@Parameter(description = "user id for new user")@PathVariable("id") @Min(1) Long id) {
		User user = null;
		try {
			user = userService.getUserById(id);
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
		}
		
		return new ResponseEntity<>(user, HttpStatus.OK);
		
	}

	
	

	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable("id") Long id){
		userService.deleteUserById(id);
	}
	
	@PutMapping(path = "/{id}", consumes = "application/json")
	public ResponseEntity<User> updateUserById(@PathVariable("id") Long id, @RequestBody User user){
		User result = null;
		try {
			result = userService.updateUser(id, user);
				
		}catch(UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
		}
		return new ResponseEntity<>(result,HttpStatus.CREATED);
	
	}
	
	@GetMapping("/username/{username}")
	public User getByUsername(@PathVariable("username") String username) throws UserNameNotFoundException{
		Optional<User> user = userService.getUserByUsername(username);
		if(user.isEmpty()) {
			throw new UserNameNotFoundException("user by username : "+username+" doesn't exist");
		}
		return user.get();
		
		
	}
	
}

