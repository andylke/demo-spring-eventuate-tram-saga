package com.github.andylke.demo.transaction;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

  private static final Logger LOGGER = LoggerFactory.getLogger(TransactionService.class);

  @Autowired private TransactionRepository repository;

  @Autowired private ModelMapper modelMapper;

  @Transactional
  public AddTransactionResponse addTransaction(AddTransactionRequest request) {
    final Transaction entity = modelMapper.map(request, Transaction.class);
    entity.setTransactionState(TransactionState.PENDING);

    final Transaction savedEntity = repository.save(entity);
    final AddTransactionResponse response =
        modelMapper.map(savedEntity, AddTransactionResponse.class);

    LOGGER.info("Added Transaction {}", response);
    return response;
  }

  @Transactional
  public RejectTransactionResponse rejectTransaction(RejectTransactionRequest request)
      throws TransactionNotFoundException {
    Transaction entity =
        repository
            .findByTransactionId(request.getTransactionId())
            .orElseThrow(() -> new TransactionNotFoundException());

    entity.setTransactionState(TransactionState.REJECTED);
    entity.setRemarks(request.getRemarks());

    Transaction savedEntity = repository.save(entity);
    RejectTransactionResponse response =
        modelMapper.map(savedEntity, RejectTransactionResponse.class);

    LOGGER.info("Rejected Transaction {}", response);
    return response;
  }

  @Transactional
  public ApproveTransactionResponse approveTransaction(ApproveTransactionRequest request)
      throws TransactionNotFoundException {
    Transaction entity =
        repository
            .findByTransactionId(request.getTransactionId())
            .orElseThrow(() -> new TransactionNotFoundException());

    entity.setTransactionState(TransactionState.APPROVED);

    Transaction savedEntity = repository.save(entity);
    ApproveTransactionResponse response =
        modelMapper.map(savedEntity, ApproveTransactionResponse.class);

    LOGGER.info("Approved Transaction {}", response);
    return response;
  }
}
