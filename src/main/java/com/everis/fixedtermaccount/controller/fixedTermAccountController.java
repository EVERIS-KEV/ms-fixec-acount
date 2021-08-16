package com.everis.fixedtermaccount.controller;

import com.everis.fixedtermaccount.dto.message;
import com.everis.fixedtermaccount.model.fixedTermAccount;
import com.everis.fixedtermaccount.model.movements;
import com.everis.fixedtermaccount.service.fixedTermAccountService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.*;

@RestController
@CrossOrigin(
  origins = "*",
  methods = {
    RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE
  }
)
@RequestMapping
public class fixedTermAccountController {
  @Autowired
  fixedTermAccountService service;

  @PostMapping("/save")
  public Mono<Object> created(
    @RequestBody @Valid fixedTermAccount model,
    BindingResult bindinResult
  ) {
    String msg = "";

    if (bindinResult.hasErrors()) {
      for (int i = 0; i < bindinResult.getAllErrors().size(); i++) msg =
        bindinResult.getAllErrors().get(0).getDefaultMessage();
      return Mono.just(new message(msg));
    }

    return service.save(model);
  }

  @PostMapping("/movememts")
  public Mono<Object> registedMovememts(
    @RequestBody @Valid movements model,
    BindingResult bindinResult
  ) {
    String msg = "";

    if (bindinResult.hasErrors()) {
      for (int i = 0; i < bindinResult.getAllErrors().size(); i++) msg =
        bindinResult.getAllErrors().get(0).getDefaultMessage();
      return Mono.just(new message(msg));
    }

    return service.saveMovements(model);
  }
  
  @PostMapping("/addTransfer")
  public Mono<Object> addTransfer(
    @RequestBody @Valid movements model,
    BindingResult bindinResult
  ) {
    String msg = "";

    if (bindinResult.hasErrors()) {
      for (int i = 0; i < bindinResult.getAllErrors().size(); i++) msg =
        bindinResult.getAllErrors().get(0).getDefaultMessage();
      return Mono.just(new message(msg));
    }

    return service.saveTransfer(model);
  }

  @GetMapping("/")
  public Flux<Object> findAll() {
    return service.getAll();
  }

  @GetMapping("/byNumberAccount/{number}")
  public Mono<Object> findOneByNumberAccount(@PathVariable("number") String number) {
    return service.getOne(number);
  }

  @GetMapping("/verifyByNumberAccount/{number}")
  public Mono<Boolean> verifyByNumberAccount(@PathVariable("number") String number) {
    return service._verifyByNumberAccount(number);
  }
  
  @GetMapping("/byCustomer/{id}")
  public Flux<Object> findByCustomer(@PathVariable("id") String id){
	  return service.getByCustomer(id);
  }
  
}
