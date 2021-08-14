package com.everis.fixedtermaccount.consumer;

import org.springframework.web.reactive.function.client.WebClient;

public class webclient {
  public static WebClient customer = WebClient.create(
    "http://host.docker.internal:8090/service/customers"
  );
  public static WebClient logic = WebClient.create(
    "http://host.docker.internal:8090/service/logic"
  );
}
