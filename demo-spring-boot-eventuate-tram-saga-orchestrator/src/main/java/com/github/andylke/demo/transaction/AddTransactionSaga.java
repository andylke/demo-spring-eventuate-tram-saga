package com.github.andylke.demo.transaction;

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
public class AddTransactionSaga implements SimpleSaga<AddTransactionSagaData> {

  private final SagaDefinition<AddTransactionSagaData> sagaDefinition =
      step()
          .invokeLocal(this::addTransaction)
          .withCompensation(this::rejectTransaction)
          .step()
          .invokeParticipant(this::reserveCredit)
          .onReply(AccountNotFoundReply.class, this::handleAccountNotFound)
          .onReply(InsufficientFundReply.class, this::handleInsufficientFund)
          .step()
          .invokeLocal(this::approveTransaction)
          .build();

  @Autowired private TransactionService transactionService;

  @Autowired private ModelMapper modelMapper;

  @Override
  public String getSagaType() {
    return "demo-spring-boot-eventuate-tram-saga.reserve-credit";
  }

  @Override
  public SagaDefinition<AddTransactionSagaData> getSagaDefinition() {
    return sagaDefinition;
  }

  private void addTransaction(AddTransactionSagaData sagaData) {
    AddTransactionResponse response =
        transactionService.addTransaction(modelMapper.map(sagaData, AddTransactionRequest.class));
    sagaData.setTransactionId(response.getTransactionId());
  }

  private void rejectTransaction(AddTransactionSagaData sagaData) {
    transactionService.rejectTransaction(
        new RejectTransactionRequest(sagaData.getTransactionId(), sagaData.getRemarks()));
  }

  private CommandWithDestination reserveCredit(AddTransactionSagaData sagaData) {
    return CommandWithDestinationBuilder.send(
            new ReserveCreditCommand(
                new ReserveCreditRequest(
                    sagaData.getCustomerId(), sagaData.getTransactionAmount())))
        .to("demo-spring-boot-eventuate-tram-saga.reserve-credit")
        .build();
  }

  private <T extends Object> void handleAccountNotFound(
      AddTransactionSagaData sagaData, AccountNotFoundReply reply) {
    sagaData.setRemarks("Account not found");
  }

  private <T extends Object> void handleInsufficientFund(
      AddTransactionSagaData sagaData, InsufficientFundReply reply) {
    sagaData.setRemarks("Insufficient fund");
  }

  private void approveTransaction(AddTransactionSagaData sagaData) {
    transactionService.approveTransaction(
        new ApproveTransactionRequest(sagaData.getTransactionId()));
  }
}
