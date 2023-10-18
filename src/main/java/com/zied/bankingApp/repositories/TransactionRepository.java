package com.zied.bankingApp.repositories;

import com.zied.bankingApp.models.Transaction;
import com.zied.bankingApp.models.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    List<Transaction> findAllByUserId(Integer userId);

    @Query("select sum(t.amount) from Transaction t where t.user.id = :userId")
    BigDecimal findAccountBalance(Integer userId);

    @Query("select max(abs(t.amount)) from Transaction t where t.user.id = :userId and t.type =:transactionType")
    BigDecimal findHighestAmountByTransactionType(Integer userId, TransactionType transactionType);

    @Query("select t.createdDated, sum(t.amount) from Transaction t where t.user.id = :userId and  t.createdDated" +
            " between :startDate and :endDate group by t.createdDated")
    Map<LocalDate, BigDecimal> findSumTransactionsByDate(LocalDateTime startDate, LocalDateTime endDate, Integer userId);
}
