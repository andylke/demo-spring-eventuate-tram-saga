package com.github.andylke.demo.account;

import java.math.BigDecimal;

public class ReserveCreditRequest {

  private Long customerId;

  private BigDecimal amount;

  public ReserveCreditRequest() {}

  public ReserveCreditRequest(Long customerId, BigDecimal amount) {
    this.customerId = customerId;
    this.amount = amount;
  }

  public Long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }
}
