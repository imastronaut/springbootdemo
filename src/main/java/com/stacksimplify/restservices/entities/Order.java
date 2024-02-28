package com.stacksimplify.restservices.entities;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "orders")
@Data
@RequiredArgsConstructor
public class Order extends RepresentationModel<Order>{
	
	@Id
	@GeneratedValue
	private Long orderId;
	private String orderDescription;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private User user;
	
}
