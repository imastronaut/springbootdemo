package com.stacksimplify.restservices.Hello;

import lombok.Data;

@Data
public class UserDetails {
	
	
	
	
	private String firstName;
	private String lastName;
	private String city;
	public UserDetails(String firstName, String lastName, String city) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
	}

}
