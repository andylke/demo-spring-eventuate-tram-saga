package com.github.andylke.demo.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/orders")
public class OrderRestController {

  @Autowired private OrderSagaService service;

  @PostMapping
  public AddOrderResponse addOrder(@RequestBody AddOrderRequest request) {
    return service.addOrder(request);
  }
}
