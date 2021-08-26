package com.everis.fixedtermaccount.Consumer;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import com.everis.fixedtermaccount.Constants.*;

public class webclient {

	public static final WebClient logic = WebClient.create(Constants.Path.LOGIC_PATH);
	public static final WebClient customer = WebClient.create(Constants.Path.CUSTOMERS_PATH);

	public static WebClient creditAccount = WebClient.create(Constants.Path.CREDIT_PATH);

	public static WebClient currentAccount = WebClient.builder().baseUrl(Constants.Path.CURRENT_ACCOUNT_PATH)
			.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();

	public static WebClient savingAccount = WebClient.builder().baseUrl(Constants.Path.SAVED_ACCOUNT_PATH)
			.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();

	public static WebClient fixedAccount = WebClient.builder().baseUrl(Constants.Path.FIXED_ACCOUNT_PATH)
			.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
}
