package com.monbuy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.monbuy.entity.User;
import com.monbuy.repository.UserRepository;
@Service
public class UserServiceImpl implements UserServices {
    
    private UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    //@Autowired
   // private UserRepository userRepository;
   //this was autowired before but now using constructor injection  
    @Override
    public User CreateUser(User user) {
        userRepository.save(user);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }
    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
    
    
}
