package com.everis.fixedtermaccount.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.everis.fixedtermaccount.dto.message;
import com.everis.fixedtermaccount.dto.movements;
import com.everis.fixedtermaccount.model.fixedTermAccount;
import com.everis.fixedtermaccount.service.fixedTermAccountService;

import reactor.core.publisher.*; 

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/fixedAccount")  
public class fixedTermAccountController {
	
	@Autowired
	fixedTermAccountService service;
	
	@PostMapping("/save")
	public Mono<Object> created(@RequestBody @Valid fixedTermAccount model, BindingResult bindinResult){
		String msg = ""; 
		
		if (bindinResult.hasErrors()) {
			for (int i = 0; i < bindinResult.getAllErrors().size(); i++) 
				msg = bindinResult.getAllErrors().get(0).getDefaultMessage();
			return Mono.just(new message(msg));
		} 
		
		return service.save(model);
	}
	
	@PostMapping("/movememts")
	public Mono<Object> registedMovememts(@RequestBody @Valid movements model, BindingResult bindinResult){
		String msg = ""; 
		
		if (bindinResult.hasErrors()) {
			for (int i = 0; i < bindinResult.getAllErrors().size(); i++) 
				msg = bindinResult.getAllErrors().get(0).getDefaultMessage();
			return Mono.just(new message(msg));
		} 
		
		return service.saveMovements(model);
	}
	
	@GetMapping("/")
	public Flux<Object> findAll(){
		return service.getAll();
	}
	
	@GetMapping("/{number}")
	public Mono<Object> findOneByNumberAccount(@PathVariable("number") String number){ 
		return service.getOne(number);
	}
}
