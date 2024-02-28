package com.stacksimplify.restservices.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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
	
	@NotEmpty(message = "Username is a mandatory field, Please provide username")
	@Column(name = "user_name", length = 50, nullable = false, unique = true)
	private String username;
	
	@Size(min=2, message = "Fristname should have atleast 2 characters")
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
	
	@OneToMany(mappedBy = "user")
	private List<Order> orders;
	
}
