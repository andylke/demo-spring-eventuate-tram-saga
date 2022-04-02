package com.github.andylke.demo.transaction;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ApproveTransactionRequest {

  private Long transactionId;

  public ApproveTransactionRequest() {}

  public ApproveTransactionRequest(Long transactionId) {
    this.transactionId = transactionId;
  }

  public Long getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(Long transactionId) {
    this.transactionId = transactionId;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.NO_CLASS_NAME_STYLE);
  }
}
