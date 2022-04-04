package com.github.andylke.demo.order;

public class RejectOrderRequest {

  private Long orderId;

  private String remarks;

  public RejectOrderRequest() {}

  public RejectOrderRequest(Long orderId, String remarks) {
    this.orderId = orderId;
    this.remarks = remarks;
  }

  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }
}
