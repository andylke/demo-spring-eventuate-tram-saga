package com.github.andylke.demo.transaction;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class RejectTransactionResponse {

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

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.NO_CLASS_NAME_STYLE);
  }
}
