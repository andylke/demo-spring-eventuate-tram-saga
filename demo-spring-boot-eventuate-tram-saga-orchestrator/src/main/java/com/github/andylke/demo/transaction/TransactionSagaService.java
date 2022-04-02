package com.github.andylke.demo.transaction;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.eventuate.tram.sagas.orchestration.SagaInstanceFactory;

@Service
public class TransactionSagaService {

  @Autowired private SagaInstanceFactory sagaInstanceFactory;

  @Autowired private AddTransactionSaga addTransactionSaga;

  @Autowired private ModelMapper modelMapper;

  @Transactional
  public AddTransactionResponse addTransaction(AddTransactionRequest request) {
    final AddTransactionSagaData sagaData = modelMapper.map(request, AddTransactionSagaData.class);
    sagaInstanceFactory.create(addTransactionSaga, sagaData);
    return modelMapper.map(sagaData, AddTransactionResponse.class);
  }
}
