package com.github.andylke.demo.account;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import io.eventuate.tram.commands.consumer.CommandDispatcher;
import io.eventuate.tram.commands.consumer.CommandDispatcherFactory;
import io.eventuate.tram.spring.commands.consumer.TramCommandConsumerConfiguration;
import io.eventuate.tram.spring.optimisticlocking.OptimisticLockingDecoratorConfiguration;

@Configuration
@Import({TramCommandConsumerConfiguration.class, OptimisticLockingDecoratorConfiguration.class})
public class ReserveCreditCommandConfiguration {

  @Bean
  public ReserveCreditCommandHandler reserveCreditCommandHandler() {
    return new ReserveCreditCommandHandler();
  }

  @Bean
  public CommandDispatcher reserveCreditCommandDispatcher(
      CommandDispatcherFactory commandDispatcherFactory,
      ReserveCreditCommandHandler reserveCreditCommandHandler) {
    return commandDispatcherFactory.make(
        "ReserveCreditCommandHandler", reserveCreditCommandHandler.getCommandHandlers());
  }
}
