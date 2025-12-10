package com.monbuy.notifications.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;



@Configuration
public class JackSonConfig {
    @Bean
    ObjectMapper objectMapper(){
         //this is used to convert Java objects to JSON and vice versa
        ObjectMapper mapper = new ObjectMapper();
         
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
       // what does this do the registerModule?
       // It registers all available modules with the ObjectMapper, enabling it to handle various data types and formats.
       //any other way?
       //You can manually register specific modules based on your requirements, such as the JavaTimeModule for Java 8 date/time types.
       //i am new to this library, can you explain more?
       //Sure! Jackson is a popular library for working with JSON in Java. The ObjectMapper is the core class that provides functionality for converting between Java objects and JSON.
       // By registering modules, you extend the capabilities of the ObjectMapper to handle additional data types and formats that are not supported by default.
       //there i saw many differnet methods like serialize, deserialize, readValue, writeValueAsString etc
         //Yes, those methods are used for different purposes:
         //- serialize: Converts a Java object into JSON format.
         //- deserialize: Converts JSON data back into a Java object.
         //- readValue: Reads JSON data and converts it into a Java object.
         //- writeValueAsString: Converts a Java object into a JSON string.
         //These methods make it easy to work with JSON data in your Java applications.
         /// what comes next after this registermodule?
         //After registering the necessary modules, you can use the ObjectMapper instance to perform serialization and deserialization operations as needed in your application.
         //paramters of registermodule?
         //The registerModule method takes a Module object as a parameter. You can create instances of specific modules, such as JavaTimeModule, and pass them to the registerModule method to enable support for specific data types.
        
        return mapper;
    }
}
