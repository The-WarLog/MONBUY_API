package com.monbuy.services;

import java.util.List;

import com.monbuy.dto.TransactionDto;
import com.monbuy.entity.Transaction;

public interface TransactionServices {
    
   void createTransaction(Transaction transaction);
    TransactionDto getTransactionById(Long id);
    List<TransactionDto> getAllTransactions();
}
