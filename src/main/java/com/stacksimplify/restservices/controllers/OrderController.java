package com.stacksimplify.restservices.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stacksimplify.restservices.entities.Order;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.OrderDoesNotExistException;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.repositories.OrderRepository;
import com.stacksimplify.restservices.repositories.UserRepository;

@RestController
@RequestMapping("/users")
public class OrderController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@GetMapping("/{userId}/orders")
	public List<Order> getAllOrders(@PathVariable("userId") Long userId) throws UserNotFoundException{
		Optional<User> user = userRepository.findById(userId);
		if(user.isEmpty()) {
			throw new UserNotFoundException("User not Found");
		}
		return user.get().getOrders();
	}
	
	@PostMapping("/{userId}/orders")
	public Order createOrder(@PathVariable("userId") Long userId, @RequestBody Order order) throws UserNotFoundException {
		Optional<User> user = userRepository.findById(userId);
		if(user.isEmpty()) {
			throw new UserNotFoundException("User not Found");
		}
		order.setUser(user.get());
		return orderRepository.save(order);
		
		
	}
	
	@GetMapping("/{userId}/orders/{orderId}")
	public Order getOrderByOrderId(@PathVariable("userId") Long userId, @PathVariable("orderId") Long orderId) throws UserNotFoundException, OrderDoesNotExistException{
		Optional<User> user = userRepository.findById(userId);
		if(user.isEmpty()) {
			throw new UserNotFoundException("User not Found, unable to find order");
		}
		Optional<Order> order = orderRepository.findById(orderId);
		if(order.isEmpty()) {
			throw new OrderDoesNotExistException("Order with orderId : "+ orderId+" does not exist");
		}
		if(!user.get().getOrders().contains(order.get())) {
			throw new OrderDoesNotExistException("Order with orderId : "+orderId+ " does not exist for user with userId : "+userId);
		}
		return order.get();
	}

}
