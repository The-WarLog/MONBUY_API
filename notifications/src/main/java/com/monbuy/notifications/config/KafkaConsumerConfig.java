package com.monbuy.notifications.config;

import java.util.HashMap;


import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.monbuy.notifications.entity.Transaction;

@Configuration
public class KafkaConsumerConfig {
    ConsumerFactory<String ,Transaction> consumerFactory(){
        JsonDeserializer<Transaction> jsonDeserialize= new JsonDeserializer<>();
        
        HashMap<String,Object> props= new HashMap<>(); 
        return null;
    }
    
}
