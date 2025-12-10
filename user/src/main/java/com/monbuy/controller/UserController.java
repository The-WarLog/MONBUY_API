package com.monbuy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monbuy.entity.User;
import com.monbuy.service.UserServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/v1/users")
public class UserController {
 
@Autowired
private UserServiceImpl userService;

@PostMapping("/create")
public ResponseEntity<User> CreateUser(@RequestBody User user) {
    
    
    return ResponseEntity.ok(userService.CreateUser(user));
}
@GetMapping("/{id}")
public ResponseEntity<User> getUser(@PathVariable Long id) {
    return  ResponseEntity.ok(userService.getUserById(id).orElse(null)); //orElse to handle Optional
}

@GetMapping("/all")
public ResponseEntity<?> getAllUsers() {
    return  ResponseEntity.ok(userService.getAllUsers()); 
}




}
