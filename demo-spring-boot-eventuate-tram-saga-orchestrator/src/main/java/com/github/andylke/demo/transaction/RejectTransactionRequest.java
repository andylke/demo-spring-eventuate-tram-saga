package com.github.andylke.demo.transaction;

public class RejectTransactionRequest {

  private Long transactionId;

  private String remarks;

  public RejectTransactionRequest() {}

  public RejectTransactionRequest(Long transactionId, String remarks) {
    this.transactionId = transactionId;
    this.remarks = remarks;
  }

  public Long getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(Long transactionId) {
    this.transactionId = transactionId;
  }

  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }
}
