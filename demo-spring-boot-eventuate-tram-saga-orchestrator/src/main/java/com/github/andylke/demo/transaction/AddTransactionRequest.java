package com.github.andylke.demo.transaction;

import java.math.BigDecimal;

public class AddTransactionRequest {

  private Long customerId;

  private BigDecimal transactionAmount;

  public Long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }

  public BigDecimal getTransactionAmount() {
    return transactionAmount;
  }

  public void setTransactionAmount(BigDecimal transactionAmount) {
    this.transactionAmount = transactionAmount;
  }
}
