package com.stacksimplify.restservices.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@Table(name = "`user`")
@RequiredArgsConstructor
public class User {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "user_name", length = 50, nullable = false, unique = true)
	private String username;
	
	@Column(name = "first_name", length = 50, nullable= false)
	private String firstname;
	@Column(name = "last_name", length = 50, nullable= false)
	private String lastname;
	@Column(name = "email_address", length = 50, nullable= false)
	private String email;
	@Column(name = "role", length = 50, nullable= false)
	private String role;
	
	@Column(name = "ssn", length = 50, nullable = false, unique = true)
	private String ssn;
	
}
