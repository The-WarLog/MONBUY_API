package com.monbuy.kafka;

import java.util.concurrent.CompletableFuture;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.monbuy.entity.Transaction;

@Component
public class KafkaEventProducer {
    private static final String TOPIC = "transactions_topic";

    private final KafkaTemplate<String,String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    //@Autowired
    //constructor injection
    public KafkaEventProducer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
        this.objectMapper.registerModule(new JavaTimeModule());
    }

   public void sendTransactionEvent(String key, Transaction transaction) {
        System.out.println("📤 Sending to Kafka → Topic: " + TOPIC + ", Key: " + key + ", Message: " + transaction);

        try {
            String transactionJson = objectMapper.writeValueAsString(transaction);
            CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(TOPIC, key, transactionJson);
        
            future.thenAccept(result -> {
                RecordMetadata metadata = result.getRecordMetadata();
                System.out.println("✅ Kafka message sent successfully! Topic: " + metadata.topic() + ", Partition: " + metadata.partition() + ", Offset: " + metadata.offset());
            }).exceptionally(ex -> {
                System.err.println("❌ Failed to send Kafka message: " + ex.getMessage());
                ex.printStackTrace();
                return null;
            });
        } catch (Exception e) {
            System.err.println("❌ Failed to serialize transaction: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
