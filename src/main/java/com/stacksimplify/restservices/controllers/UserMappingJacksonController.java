package com.stacksimplify.restservices.controllers;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.services.UserService;

import jakarta.validation.constraints.Min;

@RestController
@RequestMapping("/jacksonfilter/users")
@Validated
public class UserMappingJacksonController {
	
	
	@Autowired
	private UserService userService;
	
	public UserMappingJacksonController(UserService userService) {
		this.userService = userService;
	}
	
	//Fields with hashset
	@GetMapping("/{id}")
	public MappingJacksonValue getUserById(@PathVariable("id") @Min(1) Long id) {
		User user = null;
		try {
			user = userService.getUserById(id);
			MappingJacksonValue mapper = new MappingJacksonValue(user);
			Set<String> fields = new HashSet<>();
			fields.add("id");
			fields.add("username");
			fields.add("ssn");
			fields.add("orders");
			SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(fields );
			FilterProvider filterProvider = new SimpleFilterProvider()
					.addFilter("userFilter", filter );
			mapper.setFilters(filterProvider);
			return mapper;
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
		}
		
		
		
	}
	
	@GetMapping("/params/{id}")
	public MappingJacksonValue getUserById2(@PathVariable("id") @Min(1) Long id, @RequestParam Set<String> fields ) {
		User user = null;
		try {
			user = userService.getUserById(id);
			MappingJacksonValue mapper = new MappingJacksonValue(user);
			SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(fields );
			FilterProvider filterProvider = new SimpleFilterProvider()
					.addFilter("userFilter", filter );
			mapper.setFilters(filterProvider);
			return mapper;
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
		}
		
		
		
	}


}
