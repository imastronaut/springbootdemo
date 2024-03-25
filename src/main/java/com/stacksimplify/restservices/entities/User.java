package com.stacksimplify.restservices.entities;

import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

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
//@JsonIgnoreProperties({"firstname", "lastname"}) -- Static Filtering JSON Ignore
@RequiredArgsConstructor
//@JsonFilter(value = "userFilter")  - Used for ammping jackson value filtering section
public class User extends RepresentationModel<User>{
	
	@Id
	@GeneratedValue
	@JsonView(Views.External.class)
	private Long id;
	
	@NotEmpty(message = "Username is a mandatory field, Please provide username")
	@Column(name = "user_name", length = 50, nullable = false, unique = true)
	@JsonView(Views.External.class)
	private String username;
	
	@Size(min=2, message = "Fristname should have atleast 2 characters")
	@Column(name = "first_name", length = 50, nullable= false)
	@JsonView(Views.External.class)
	private String firstname;
	@Column(name = "last_name", length = 50, nullable= false)
	@JsonView(Views.External.class)
	private String lastname;
	@Column(name = "email_address", length = 50, nullable= false)
	@JsonView(Views.External.class)
	private String email;
	@Column(name = "role", length = 50, nullable= false)
	@JsonView(Views.Internal.class)
	private String role;
	
	@Column(name = "ssn", length = 50, nullable = false, unique = true)
//	@JsonIgnore    Static Filtering JSONIgnore
	@JsonView(Views.Internal.class)
	private String ssn;
	
	@OneToMany(mappedBy = "user")
	@JsonView(Views.Internal.class)
	private List<Order> orders;
	
}
