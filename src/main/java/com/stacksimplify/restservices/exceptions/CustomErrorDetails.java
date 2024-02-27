package com.stacksimplify.restservices.exceptions;

import java.util.Date;

import lombok.Data;

//Simple custom error details bean
@Data
public class CustomErrorDetails {
	
	
	private Date timestamp;
	private String message;
	private String errordetails;
	
	public CustomErrorDetails(Date timestamp, String message, String errordetails) {
		// TODO Auto-generated constructor stub
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.errordetails = errordetails;
	}
}
