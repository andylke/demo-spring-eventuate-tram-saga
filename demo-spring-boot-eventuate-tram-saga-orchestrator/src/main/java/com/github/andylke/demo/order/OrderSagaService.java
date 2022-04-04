package com.github.andylke.demo.order;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.eventuate.tram.sagas.orchestration.SagaInstanceFactory;

@Service
public class OrderSagaService {

  @Autowired private SagaInstanceFactory sagaInstanceFactory;

  @Autowired private AddOrderSaga addOrderSaga;

  @Autowired private ModelMapper modelMapper;

  @Transactional
  public AddOrderResponse addOrder(AddOrderRequest request) {
    final AddOrderSagaData sagaData = modelMapper.map(request, AddOrderSagaData.class);
    sagaInstanceFactory.create(addOrderSaga, sagaData);
    return modelMapper.map(sagaData, AddOrderResponse.class);
  }
}
