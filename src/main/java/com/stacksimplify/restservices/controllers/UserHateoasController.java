package com.stacksimplify.restservices.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimplify.restservices.entities.Order;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.repositories.UserRepository;
import com.stacksimplify.restservices.services.UserService;

import jakarta.validation.constraints.Min;

@RestController
@RequestMapping("/hateoas/users")
public class UserHateoasController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") @Min(1) Long id) {
		User user = null;
		try {
			user = userService.getUserById(id);
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
		}
		Long userId = user.getId();
		Link selfLink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(userId).withSelfRel();
		user.add(selfLink);
		return new ResponseEntity<>(user, HttpStatus.OK);
		
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public CollectionModel<User> getAllUsers() throws UserNotFoundException {
		List<User> users= userService.getAllUsers(); 
		for(User user : users) {
			Long userId = user.getId();
			Link selfLink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(userId).withSelfRel();
			user.add(selfLink);
			
			//Relationship link with getAllOrders
			CollectionModel<Order> orders = WebMvcLinkBuilder.methodOn(OrderHateoasController.class).getAllOrders(userId);
			Link ordersLink = WebMvcLinkBuilder.linkTo(orders).withRel("all-orders");
			user.add(ordersLink);
		}
		
		//SelfLink for getAllUsers
		Link getAllUsersSelfLink = WebMvcLinkBuilder.linkTo(this.getClass()).withSelfRel();
		
		CollectionModel<User> finalCollection = CollectionModel.of(users, getAllUsersSelfLink);
		return finalCollection;
	}

}
