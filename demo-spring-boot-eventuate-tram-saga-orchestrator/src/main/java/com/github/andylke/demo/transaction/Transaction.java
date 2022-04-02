package com.github.andylke.demo.transaction;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Transaction {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long transactionId;

  private Long customerId;

  private BigDecimal transactionAmount;

  @Enumerated(EnumType.STRING)
  private TransactionState transactionState;

  private String remarks;

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

  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }
}
