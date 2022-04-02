package com.github.andylke.demo.transaction;

import java.util.Optional;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

  @Lock(LockModeType.PESSIMISTIC_READ)
  Optional<Transaction> findByTransactionId(Long transactionId);
}
