package com.github.andylke.demo.account;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import io.eventuate.tram.commands.common.Command;

public class ReserveCreditCommand implements Command {

  private ReserveCreditRequest request;

  public ReserveCreditCommand() {}

  public ReserveCreditCommand(ReserveCreditRequest request) {
    this.request = request;
  }

  public ReserveCreditRequest getRequest() {
    return request;
  }

  public void setRequest(ReserveCreditRequest request) {
    this.request = request;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(request, ToStringStyle.NO_CLASS_NAME_STYLE);
  }
}
