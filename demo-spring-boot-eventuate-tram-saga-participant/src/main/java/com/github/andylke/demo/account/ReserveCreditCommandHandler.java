package com.github.andylke.demo.account;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import io.eventuate.tram.commands.consumer.CommandHandlerReplyBuilder;
import io.eventuate.tram.commands.consumer.CommandHandlers;
import io.eventuate.tram.commands.consumer.CommandMessage;
import io.eventuate.tram.messaging.common.Message;
import io.eventuate.tram.sagas.participant.SagaCommandHandlersBuilder;

public class ReserveCreditCommandHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(ReserveCreditCommandHandler.class);

  private final CommandHandlers commandHandlers =
      SagaCommandHandlersBuilder.fromChannel("demo-spring-boot-eventuate-tram-saga.reserve-credit")
          .onMessage(ReserveCreditCommand.class, this::addReservedCredit)
          .build();

  @Autowired private AccountService service;

  public CommandHandlers getCommandHandlers() {
    return commandHandlers;
  }

  public Message addReservedCredit(CommandMessage<ReserveCreditCommand> message) {
    final ReserveCreditCommand command = message.getCommand();
    LOGGER.info("Received ReserveCreditCommand {}", command);

    try {
      service.reserveCredit(command.getRequest());
      return CommandHandlerReplyBuilder.withSuccess();

    } catch (AccountNotFoundException e) {
      return CommandHandlerReplyBuilder.withFailure(new AccountNotFoundReply());

    } catch (InsufficientFundException e) {
      return CommandHandlerReplyBuilder.withFailure(new InsufficientFundReply());
    }
  }
}
