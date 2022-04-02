package com.github.andylke.demo.account;

import java.math.BigDecimal;

public class ReserveCreditResponse {

  private Long accountId;

  private Long customerId;

  private BigDecimal availableAmount;

  private BigDecimal reservedAmount;

  public Long getAccountId() {
    return accountId;
  }

  public void setAccountId(Long accountId) {
    this.accountId = accountId;
  }

  public Long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }

  public BigDecimal getAvailableAmount() {
    return availableAmount;
  }

  public void setAvailableAmount(BigDecimal availableAmount) {
    this.availableAmount = availableAmount;
  }

  public BigDecimal getReservedAmount() {
    return reservedAmount;
  }

  public void setReservedAmount(BigDecimal reservedAmount) {
    this.reservedAmount = reservedAmount;
  }
}
