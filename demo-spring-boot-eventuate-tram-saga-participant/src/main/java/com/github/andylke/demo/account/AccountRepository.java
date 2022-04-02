package com.github.andylke.demo.account;

import java.util.Optional;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

  @Lock(LockModeType.PESSIMISTIC_READ)
  Optional<Account> findByCustomerId(Long customerId);
}
