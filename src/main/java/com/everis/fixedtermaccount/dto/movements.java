package com.everis.fixedtermaccount.dto;

import java.util.Date;

import javax.validation.constraints.*;

import lombok.*; 

@Getter 
@Setter       
public class movements {  
	@NotBlank(message = "Debe seleccionar un numero de cuenta.")
	private String accountNumber; 
	@NotBlank(message = "Debe seleccionar un typo de movimiento.")
	private String type;
	private String date;   
	@Min(10)
	private double amount;  
	
	private movements( ) {
		this.date = new Date().toString(); 
	}
	
	private movements(String accountNumber, String type, double amount) {
		this.date = new Date().toString();
		this.type=type;
		this.amount=amount;
	}
}

