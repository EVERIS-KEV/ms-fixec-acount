package com.everis.fixedtermaccount.Model;

import java.time.*;
import java.util.*;

import javax.validation.constraints.NotBlank;

import com.everis.fixedtermaccount.Consumer.webclient;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "fixed-term-account")
public class fixedTermAccount {
	@Id
	private String idFixedTermAccount;
	private String accountNumber = webclient.logic.get().uri("/generatedNumberLong/12").retrieve()
			.bodyToMono(String.class).block();
	private LocalDateTime dateCreated = LocalDateTime.now(ZoneId.of("America/Lima"));

	private double amount = 0.0;
	private String typeAccount = "Cuenta a plazo fijo.";
	
	private List<movements> movements = new ArrayList<movements>();
	private String profile;

	@NotBlank(message = "Debe seleccionar un cliente.")
	private String idCustomer;

	public fixedTermAccount(String idCustomer) {
		this.idCustomer = idCustomer;
	}

	public fixedTermAccount(String profile, String idCustomer) {
		this.profile = profile;
		this.idCustomer = idCustomer;
	}
}
