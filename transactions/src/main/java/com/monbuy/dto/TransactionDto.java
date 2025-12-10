package com.monbuy.dto;

import com.monbuy.entity.Transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
@AllArgsConstructor
public class TransactionDto {
    public Long id;
    public String senderName;
    public String senderAccountNumber;
    public String receiverName;
    public String receiverAccountNumber;
    public Double amount;
    public String status;
    
    // Note: transactionDate is intentionally omitted in the DTO
    //mapper methods can be added here if needed
    public TransactionDto fromTransaction(Transaction transaction){
        return new TransactionDto(
            transaction.getId(),
            transaction.getSenderName(),
            transaction.getSenderAccountNumber(),
            transaction.getReceiverName(),
            transaction.getReceiverAccountNumber(),
            transaction.getAmount(),
            transaction.getStatus());
    }
}
