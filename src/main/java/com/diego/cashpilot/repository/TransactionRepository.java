package com.diego.cashpilot.repository;

import com.diego.cashpilot.model.Transaction;
import com.diego.cashpilot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository  extends JpaRepository<Transaction, Long> {

    List<Transaction> findByUser(User user);
}
