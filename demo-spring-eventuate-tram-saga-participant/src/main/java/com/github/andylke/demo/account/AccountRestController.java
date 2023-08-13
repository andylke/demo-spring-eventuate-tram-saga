package com.github.andylke.demo.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/accounts")
public class AccountRestController {

  @Autowired private AccountService service;

  @PostMapping(path = "/reserve-credit")
  public ReserveCreditResponse reserveCredit(@RequestBody ReserveCreditRequest request)
      throws AccountNotFoundException, InsufficientFundException {
    return service.reserveCredit(request);
  }
}
