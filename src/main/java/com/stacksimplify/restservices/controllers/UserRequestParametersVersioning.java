package com.stacksimplify.restservices.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimplify.restservices.dtos.UserDtoV1;
import com.stacksimplify.restservices.dtos.UserDtoV2;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.services.UserService;

import jakarta.validation.constraints.Min;

@RestController
@RequestMapping("versioning/params/users")
public class UserRequestParametersVersioning {
	
	@Autowired
	private  ModelMapper mapper;
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping(value = "{id}", params = "version=1")
	public ResponseEntity<UserDtoV1> getUserById(@PathVariable("id") @Min(1) Long id) {
		User user = null;
		try {
			user = userService.getUserById(id);
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
		}
		
		UserDtoV1 userDto = mapper.map(user, UserDtoV1.class);
	
		return new ResponseEntity<>(userDto, HttpStatus.OK);
		
	}
	
	@GetMapping( value = "{id}", params = "version=2")
	public ResponseEntity<UserDtoV2> getUserById2(@PathVariable("id") @Min(1) Long id) {
		User user = null;
		try {
			user = userService.getUserById(id);
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
		}
		
		UserDtoV2 userDto = mapper.map(user, UserDtoV2.class);
	
		return new ResponseEntity<>(userDto, HttpStatus.OK);
		
	}
	
	
}
