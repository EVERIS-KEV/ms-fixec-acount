package com.everis.fixedtermaccount.model;

import java.util.*; 

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.everis.fixedtermaccount.dto.movements;
import com.everis.fixedtermaccount.logic.generatedId;

import lombok.*; 

@Getter 
@Setter   
@Document(collection  = "fixed-term-account") 
public class fixedTermAccount {
	@Id
	private String idFixedTermAccount;
	private String accountNumber;
	@NotBlank(message = "Debe seleccionar un cliente.")
	private String idCustomer;
	private String dateCreated; 
	private double amount;
	private List<movements> movements;
	
	public fixedTermAccount() {  
		this.accountNumber = generatedId.numberAccount(12);
		this.dateCreated = new Date().toString();
		this.amount = 0.0;
		this.movements = new ArrayList<movements>();
	}  
	
	public fixedTermAccount(String idCustomer) { 
		this.idCustomer = idCustomer;
		this.accountNumber = generatedId.numberAccount(12);
		this.dateCreated = new Date().toString();
		this.amount = 0.0;
		this.movements = new ArrayList<movements>();
	} 

	public fixedTermAccount( String accountNumber, String idCustomer, String dateCreated) {  
		this.accountNumber = accountNumber;
		this.idCustomer = idCustomer;
		this.dateCreated = dateCreated;
		this.amount = 0.0;
		this.movements = new ArrayList<movements>();
	} 
	
}
