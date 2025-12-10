package com.monbuy.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import com.monbuy.dto.TransactionDto;
import com.monbuy.entity.Transaction;
import com.monbuy.services.TransactionServicesImpl;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RequestMapping("/api/v1/transactions")
@RestController
public class TransactionController {
    @Autowired private TransactionServicesImpl transactionServicesImpl;
    //@Autowired private TransactionDto transactionDto;

    @PostMapping("/maketransaction")
    public ResponseEntity<?> MakeTransaction(@RequestBody Transaction transaction) {
        transactionServicesImpl.createTransaction(transaction);
        //transactionDto.fromTransaction(transaction);
        
        
        return ResponseEntity.ok("Transaction created successfully");
    }

    @GetMapping("/{id}")
    public TransactionDto GetTransactionById(@PathVariable Long id){
        TransactionDto findTheTransaction= transactionServicesImpl.getTransactionById(id);
        return findTheTransaction;

    }

    @GetMapping("/all")
    public ResponseEntity<?> GetAllTransactions(){
        return ResponseEntity.ok(transactionServicesImpl.getAllTransactions());
    }
    
    
}
