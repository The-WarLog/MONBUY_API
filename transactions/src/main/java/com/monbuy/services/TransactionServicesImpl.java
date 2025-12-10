package com.monbuy.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.monbuy.dto.TransactionDto;
import com.monbuy.entity.Transaction;
import com.monbuy.kafka.KafkaEventProducer;
import com.monbuy.repository.TransactionRepo;

@Service
public class TransactionServicesImpl implements TransactionServices {
    @Autowired private  TransactionRepo transactionRepo;
    //@Autowired private TransactionDto transactionDto;
    @Autowired private KafkaEventProducer kafkaEventProducer;
    @Autowired private RestTemplate restTemplate;
    public TransactionServicesImpl(){};

    @Override
    public void createTransaction(Transaction transaction) {
        Transaction savedTransaction = new Transaction();
        savedTransaction.setSenderName(transaction.getSenderName());
        savedTransaction.setSenderAccountNumber(transaction.getSenderAccountNumber());
        savedTransaction.setReceiverName(transaction.getReceiverName());
        savedTransaction.setReceiverAccountNumber(transaction.getReceiverAccountNumber());
        savedTransaction.setAmount(transaction.getAmount());
        savedTransaction.setStatus("PENDING");
    
         try{
            String key =String.valueOf(savedTransaction.getId());
            kafkaEventProducer.sendTransactionEvent(key, savedTransaction);
            System.out.println("Transaction sent to Kafka successfully.");
        }catch(Exception e){
            System.err.println("Error sending transaction to Kafka: " + e.getMessage());
            e.printStackTrace();
         }
        transactionRepo.save(savedTransaction);


        
    }



    @Override
    public List<TransactionDto> getAllTransactions() {
        List<Transaction> transactions = transactionRepo.findAll();
        return transactions.stream()
    .map(transaction -> new TransactionDto(
        transaction.getId(),
        transaction.getSenderName(),
        transaction.getSenderAccountNumber(),
        transaction.getReceiverName(),
        transaction.getReceiverAccountNumber(),
        transaction.getAmount(),
        transaction.getStatus()
    ))
    .collect(Collectors.toList());
    }
   
    @Override
    public TransactionDto getTransactionById(Long id) {
        Transaction transaction = transactionRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found with id: " + id));
        return new TransactionDto().fromTransaction(transaction);
    }
    
    
}
