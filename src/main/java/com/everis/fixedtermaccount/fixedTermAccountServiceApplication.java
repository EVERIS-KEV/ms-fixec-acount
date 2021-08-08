package com.everis.fixedtermaccount;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication; 

@SpringBootApplication
public class fixedTermAccountServiceApplication {

	public static void main(String[] args) { 
		SpringApplication.run(fixedTermAccountServiceApplication.class, args);
		System.out.println("-Micro servicio cuentas a plzo fijo, activado.");
	}

}
