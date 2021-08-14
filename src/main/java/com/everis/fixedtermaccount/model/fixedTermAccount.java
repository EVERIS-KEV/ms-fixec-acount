package com.everis.fixedtermaccount.model;

import com.everis.fixedtermaccount.consumer.webclient; 
import com.everis.fixedtermaccount.dto.movements; 

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
  private String dateCreated = new Date().toString();
  private double amount = 0.0;
  private List<movements> movements = new ArrayList<movements>(); 

  @NotBlank(message = "Debe seleccionar un cliente.")
  private String idCustomer;

  public fixedTermAccount(String idCustomer) {
    this.idCustomer = idCustomer;
  }
}
