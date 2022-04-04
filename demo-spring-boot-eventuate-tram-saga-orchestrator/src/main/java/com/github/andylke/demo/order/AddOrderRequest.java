package com.github.andylke.demo.order;

import java.math.BigDecimal;

public class AddOrderRequest {

  private Long customerId;

  private BigDecimal orderAmount;

  public Long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }

  public BigDecimal getOrderAmount() {
    return orderAmount;
  }

  public void setOrderAmount(BigDecimal orderAmount) {
    this.orderAmount = orderAmount;
  }
}
