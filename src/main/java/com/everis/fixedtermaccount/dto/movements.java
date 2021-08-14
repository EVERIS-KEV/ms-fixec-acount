package com.everis.fixedtermaccount.dto;

import java.util.Date;
import javax.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class movements {
  private String date = new Date().toString();

  @NotBlank(message = "Debe seleccionar un numero de cuenta.")
  private String accountNumber;

  @NotBlank(message = "Debe seleccionar un typo de movimiento.")
  private String type;

  @Min(10)
  private double amount;

  private movements(String accountNumber, String type, double amount) {
    this.type = type;
    this.amount = amount;
    this.accountNumber = accountNumber;
  }
}
