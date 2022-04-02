package com.github.andylke.demo.transaction;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class AddTransactionResponse {

  private Long transactionId;

  private Long customerId;

  private BigDecimal transactionAmount;

  private TransactionState transactionState;

  public Long getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(Long transactionId) {
    this.transactionId = transactionId;
  }

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

  public TransactionState getTransactionState() {
    return transactionState;
  }

  public void setTransactionState(TransactionState transactionState) {
    this.transactionState = transactionState;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.NO_CLASS_NAME_STYLE);
  }
}
