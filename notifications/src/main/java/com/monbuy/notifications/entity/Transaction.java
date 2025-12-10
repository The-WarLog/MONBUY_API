package com.monbuy.notifications.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="sender_name", nullable = false)
    private String senderName;
    @Column(name="sender_account_number", nullable = false)
    private String senderAccountNumber;

    @Column(name="receiver_name", nullable = false)
    private String receiverName;
    @Column(name="receiver_account_number", nullable = false)
    private String receiverAccountNumber;

    @Column(name="amount", nullable = false)
    @Positive(message = "Amount must be positive")
    private Double amount;

    @Column(name="transaction_date", nullable = false)
    private LocalDateTime transactionDate;

    @Column(name="status", nullable = false)
    private String status;

    //Setting default value for transactionDate before persisting
    @PrePersist
    public void prePersist() {
        if (transactionDate == null) {
            transactionDate = LocalDateTime.now();
        }
        if(status==null) {
            status = "PENDING";
        }
    }
    //WHY TO STRING METHOD IS NEEDED HERE?
    // It provides a readable string representation of the Transaction object, useful for logging and debugging.
    // It helps in quickly inspecting the values of the object's fields without needing to access each field individually.
    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", senderName='" + senderName + '\'' +
                ", senderAccountNumber='" + senderAccountNumber + '\'' +
                ", receiverName='" + receiverName + '\'' +
                ", receiverAccountNumber='" + receiverAccountNumber + '\'' +
                ", amount=" + amount +
                ", transactionDate=" + transactionDate +
                ", status='" + status + '\'' +
                '}';
    }

}
