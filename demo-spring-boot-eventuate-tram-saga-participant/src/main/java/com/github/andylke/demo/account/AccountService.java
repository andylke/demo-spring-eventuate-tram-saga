package com.github.andylke.demo.account;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

  @Autowired private AccountRepository repository;

  @Autowired private ModelMapper modelMapper;

  @Transactional
  public ReserveCreditResponse reserveCredit(ReserveCreditRequest request)
      throws AccountNotFoundException, InsufficientFundException {
    final Account entity =
        repository
            .findByCustomerId(request.getCustomerId())
            .orElseThrow(() -> new AccountNotFoundException());

    if (entity.getAvailableAmount().compareTo(request.getAmount()) < 0) {
      throw new InsufficientFundException();
    }

    entity.setAvailableAmount(entity.getAvailableAmount().subtract(request.getAmount()));
    entity.setReservedAmount(entity.getReservedAmount().add(request.getAmount()));
    final Account savedEntity = repository.save(entity);

    final ReserveCreditResponse response =
        modelMapper.map(savedEntity, ReserveCreditResponse.class);

    return response;
  }
}
