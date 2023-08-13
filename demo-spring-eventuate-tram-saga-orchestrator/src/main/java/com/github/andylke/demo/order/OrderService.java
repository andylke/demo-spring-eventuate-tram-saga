package com.github.andylke.demo.order;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

  private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);

  @Autowired private OrderRepository repository;

  @Autowired private ModelMapper modelMapper;

  @Transactional
  public AddOrderResponse addOrder(AddOrderRequest request) {
    final Order entity = modelMapper.map(request, Order.class);
    entity.setOrderState(OrderState.PENDING);

    final Order savedEntity = repository.save(entity);
    final AddOrderResponse response = modelMapper.map(savedEntity, AddOrderResponse.class);

    LOGGER.info("Added Order {}", response);
    return response;
  }

  @Transactional
  public RejectOrderResponse rejectOrder(RejectOrderRequest request) throws OrderNotFoundException {
    Order entity =
        repository
            .findByOrderId(request.getOrderId())
            .orElseThrow(() -> new OrderNotFoundException());

    entity.setOrderState(OrderState.REJECTED);
    entity.setRemarks(request.getRemarks());

    Order savedEntity = repository.save(entity);
    RejectOrderResponse response = modelMapper.map(savedEntity, RejectOrderResponse.class);

    LOGGER.info("Rejected Order {}", response);
    return response;
  }

  @Transactional
  public ApproveOrderResponse approveOrder(ApproveOrderRequest request)
      throws OrderNotFoundException {
    Order entity =
        repository
            .findByOrderId(request.getOrderId())
            .orElseThrow(() -> new OrderNotFoundException());

    entity.setOrderState(OrderState.APPROVED);

    Order savedEntity = repository.save(entity);
    ApproveOrderResponse response = modelMapper.map(savedEntity, ApproveOrderResponse.class);

    LOGGER.info("Approved Order {}", response);
    return response;
  }
}
