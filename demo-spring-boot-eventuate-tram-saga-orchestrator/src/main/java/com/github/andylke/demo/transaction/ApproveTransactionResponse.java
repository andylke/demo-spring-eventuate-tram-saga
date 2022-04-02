package com.github.andylke.demo.transaction;

public class ApproveTransactionResponse {

  private Long transactionId;

  private TransactionState transactionState;

  public Long getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(Long transactionId) {
    this.transactionId = transactionId;
  }

  public TransactionState getTransactionState() {
    return transactionState;
  }

  public void setTransactionState(TransactionState transactionState) {
    this.transactionState = transactionState;
  }
}
