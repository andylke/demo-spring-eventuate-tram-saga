package com.github.andylke.demo.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/transactions")
public class TransactionRestController {

  @Autowired private TransactionSagaService service;

  @PostMapping
  public AddTransactionResponse addTransaction(@RequestBody AddTransactionRequest request) {
    return service.addTransaction(request);
  }
}
