package com.zied.bankingApp.service.impl;

import com.zied.bankingApp.dto.TransactionDto;
import com.zied.bankingApp.exceptions.ObjectsValidator;
import com.zied.bankingApp.models.Transaction;
import com.zied.bankingApp.models.TransactionType;
import com.zied.bankingApp.repositories.TransactionRepository;
import com.zied.bankingApp.service.TransactionService;
import jakarta.el.EvaluationListener;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final ObjectsValidator<TransactionDto> validator;

    @Override
    public Integer save(TransactionDto dto) {
        validator.validate(dto);
        Transaction transaction = TransactionDto.toEntity(dto);
        BigDecimal transactionMultiplier = BigDecimal.valueOf(getTransactionMultiplier(transaction.getType()));
        BigDecimal amount = transaction.getAmount().multiply(transactionMultiplier);
        transaction.setAmount(amount);
        return transactionRepository.save(transaction).getId();
    }

    @Override
    public List<TransactionDto> findAll() {
        return transactionRepository.findAll()
                .stream()
                .map(TransactionDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public TransactionDto findById(Integer id) {
        return transactionRepository.findById(id)
                .map(TransactionDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("No transaction was found with the ID: " + id));
    }

    @Override
    public void delete(Integer id) {
        // toDo check delete
        transactionRepository.deleteById(id);
    }

    private int getTransactionMultiplier(TransactionType type){
        return TransactionType.TRANSFER == type ? -1:1;
    }

    @Override
    public List<TransactionDto> findAllByUserId(Integer userId) {
        return transactionRepository.findAllByUserId(userId)
                .stream()
                .map(TransactionDto::fromEntity)
                .collect(Collectors.toList());
    }
}
