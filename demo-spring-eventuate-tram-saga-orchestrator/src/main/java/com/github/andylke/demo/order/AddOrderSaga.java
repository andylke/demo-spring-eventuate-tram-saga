package com.github.andylke.demo.order;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.andylke.demo.account.AccountNotFoundReply;
import com.github.andylke.demo.account.InsufficientFundReply;
import com.github.andylke.demo.account.ReserveCreditCommand;
import com.github.andylke.demo.account.ReserveCreditRequest;

import io.eventuate.tram.commands.consumer.CommandWithDestination;
import io.eventuate.tram.commands.consumer.CommandWithDestinationBuilder;
import io.eventuate.tram.sagas.orchestration.SagaDefinition;
import io.eventuate.tram.sagas.simpledsl.SimpleSaga;

@Component
public class AddOrderSaga implements SimpleSaga<AddOrderSagaData> {

  private final SagaDefinition<AddOrderSagaData> sagaDefinition =
      step()
          .invokeLocal(this::addOrder)
          .withCompensation(this::rejectOrder)
          .step()
          .invokeParticipant(this::reserveCredit)
          .onReply(AccountNotFoundReply.class, this::handleAccountNotFound)
          .onReply(InsufficientFundReply.class, this::handleInsufficientFund)
          .step()
          .invokeLocal(this::approveOrder)
          .build();

  @Autowired private OrderService orderService;

  @Autowired private ModelMapper modelMapper;

  @Override
  public String getSagaType() {
    return "demo-spring-boot-eventuate-tram-saga.reserve-credit";
  }

  @Override
  public SagaDefinition<AddOrderSagaData> getSagaDefinition() {
    return sagaDefinition;
  }

  private void addOrder(AddOrderSagaData sagaData) {
    AddOrderResponse response =
        orderService.addOrder(modelMapper.map(sagaData, AddOrderRequest.class));
    sagaData.setOrderId(response.getOrderId());
  }

  private void rejectOrder(AddOrderSagaData sagaData) {
    orderService.rejectOrder(
        new RejectOrderRequest(sagaData.getOrderId(), sagaData.getRemarks()));
  }

  private CommandWithDestination reserveCredit(AddOrderSagaData sagaData) {
    return CommandWithDestinationBuilder.send(
            new ReserveCreditCommand(
                new ReserveCreditRequest(
                    sagaData.getCustomerId(), sagaData.getOrderAmount())))
        .to("demo-spring-boot-eventuate-tram-saga.reserve-credit")
        .build();
  }

  private <T extends Object> void handleAccountNotFound(
      AddOrderSagaData sagaData, AccountNotFoundReply reply) {
    sagaData.setRemarks("Account not found");
  }

  private <T extends Object> void handleInsufficientFund(
      AddOrderSagaData sagaData, InsufficientFundReply reply) {
    sagaData.setRemarks("Insufficient fund");
  }

  private void approveOrder(AddOrderSagaData sagaData) {
    orderService.approveOrder(
        new ApproveOrderRequest(sagaData.getOrderId()));
  }
}
