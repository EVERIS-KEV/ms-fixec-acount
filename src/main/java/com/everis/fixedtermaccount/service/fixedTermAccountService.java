package com.everis.fixedtermaccount.service;

import com.everis.fixedtermaccount.dto.customer;
import com.everis.fixedtermaccount.dto.message;
import com.everis.fixedtermaccount.dto.movements;
import com.everis.fixedtermaccount.model.fixedTermAccount;
import com.everis.fixedtermaccount.repository.fixedTermAccountRepository;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class fixedTermAccountService {
  @Autowired
  fixedTermAccountRepository reposirtory;

  WebClient webclient = WebClient.create("http://localhost:8081");

  private Boolean verifyCustomer(String id) {
    return webclient
      .get()
      .uri("/api/customers/verifyId/{id}", id)
      .retrieve()
      .bodyToMono(Boolean.class)
      .block();
  }

  private customer customerFind(String id) {
    return webclient
      .get()
      .uri("/api/customers/{id}", id)
      .retrieve()
      .bodyToMono(customer.class)
      .block();
  }

  private double getAmountByNumber(String number) {
    return reposirtory.findByAccountNumber(number).getAmount();
  }

  private String setAmount(movements movement) {
    double val = getAmountByNumber(movement.getAccountNumber());
    fixedTermAccount model = reposirtory.findByAccountNumber(movement.getAccountNumber());

    if (movement.getType().equals("Deposito")) {
      model.setAmount(movement.getAmount() + val);
      model.getMovements().add(movement);
    } else {
      if (movement.getAmount() > val) return "Cantidad insuficiente."; else {
        model.setAmount(val - movement.getAmount());
        model.getMovements().add(movement);
      }
    }

    reposirtory.save(model);
    return "Movimiento realizado";
  }

  
  
  public Mono<Object> save(fixedTermAccount model) {
    String msg = "Cuenta creada.";    
    
    if (verifyCustomer(model.getIdCustomer())) {
      String typeCustomer = customerFind(model.getIdCustomer()).getType(); 
      
      if(typeCustomer.equals("personal")) {
    	  if (!reposirtory.existsByIdCustomer(model.getIdCustomer())) 
        	  reposirtory.save(model); 
          else msg = "Usted ya no puede tener mas cuentas fijas.";
      } else msg = "Las cuentas empresariales no deben tener cuentas a plazo fijo.";
      
    } else msg = "Cliente no registrado";

    return Mono.just(new message(msg));
  }

  public Mono<Object> saveMovements(movements model) {
    String msg = "Movimiento realizado";
    Date date = new Date();

    if( date.getDate() == 15 ) {
    	if (reposirtory.existsByAccountNumber(model.getAccountNumber())) {
    	      if (model.getType().equals("Deposito") || model.getType().equals("Retiro")) msg =
    	        setAmount(model); 
    	      else msg = "Selecione una operacion correcta.";
    	    } else msg = "Numero de cuenta incorrecto.";
    } else msg = "No puede hacer movimientos fuera de la fecha establecida (15/**/****)."; 
    
    

    return Mono.just(new message(msg));
  }

  public Flux<Object> getAll() {
    return Flux.fromIterable(reposirtory.findAll());
  }

  public Mono<Object> getOne(String id) {
    return Mono.just(reposirtory.findByAccountNumber(id));
  }
}
