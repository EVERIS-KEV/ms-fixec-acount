package com.everis.fixedtermaccount.dto;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "customers")
public class customer {
  @Id
  private String idclient;

  private String dni;
  private String firstname;
  private String lastname;
  private String type;
}
