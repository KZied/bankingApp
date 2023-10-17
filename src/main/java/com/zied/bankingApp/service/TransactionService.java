package com.zied.bankingApp.service;

import com.zied.bankingApp.dto.TransactionDto;

import java.util.List;

public interface TransactionService extends AbstractService<TransactionDto> {

    List<TransactionDto> findAllByUserId(Integer userId);
}
