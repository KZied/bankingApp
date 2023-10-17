package com.zied.bankingApp.repositories;

import com.zied.bankingApp.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
}
