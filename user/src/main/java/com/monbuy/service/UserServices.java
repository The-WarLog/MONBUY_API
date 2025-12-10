package com.monbuy.service;

import java.util.List;
import java.util.Optional;

import com.monbuy.entity.User;

public interface UserServices  {
    User CreateUser(User user);
    Optional<User> getUserById(Long id); //Optional to handle null values
    List<User> getAllUsers(); //return list of users

}
