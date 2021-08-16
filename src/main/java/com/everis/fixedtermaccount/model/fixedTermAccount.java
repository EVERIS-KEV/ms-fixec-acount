package com.everis.fixedtermaccount.model;

import com.everis.fixedtermaccount.consumer.webclient;

import java.util.*;
import lombok.*;
import javax.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "fixed-term-account")
public class fixedTermAccount {
  @Id
  private String idFixedTermAccount; 
  private String accountNumber = webclient.logic
    .get()
    .uri("/generatedNumberLong/12")
    .retrieve()
    .bodyToMono(String.class)
    .block(); 
  private Date dateCreated = new Date();  
  private double amount = 0.0;
  private List<movements> movements = new ArrayList<movements>(); 
  private String perfil;

  @NotBlank(message = "Debe seleccionar un cliente.")
  private String idCustomer;

  public fixedTermAccount(String idCustomer) {
    this.idCustomer = idCustomer;
  } 

  public fixedTermAccount(String perfil, String idCustomer) {
    this.perfil = perfil;
    this.idCustomer = idCustomer;
  }
}
