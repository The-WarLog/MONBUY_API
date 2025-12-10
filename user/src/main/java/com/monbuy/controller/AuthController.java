package com.monbuy.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monbuy.dto.SignUpReq;
import com.monbuy.entity.User;
import com.monbuy.repository.UserRepository;
import com.monbuy.utils.JWTUtil;

import io.jsonwebtoken.Jwt;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
   
    private  final  UserRepository userRepository;
  
    private final PasswordEncoder passwordEncoder;
   
    private final JWTUtil jwtUtil;

    //ANOTHER WAY TO INJECT DEPENDENCIES IS USING CONSTRUCTOR INJECTION (QUITE SHADY ACTUALLY) using @Autowired
    //@Autowired
    //private UserRepository userRepository;
    //@Autowired
    //private PasswordEncoder passwordEncoder;
    //@Autowired
    //private JWTUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignUpReq signUpReq) {
        //Optional was truly optional here needed to be ofNullable from the optional class
      Optional<User>existingUser= Optional.ofNullable(userRepository.findByEmail(signUpReq.getEmail()));//check if user with email already exists
        if(existingUser !=null){
            return ResponseEntity.badRequest().body("Email is already in use.");

        }
        User newUser =new User();
        newUser.setEmail(signUpReq.getEmail());
        newUser.setPassword(passwordEncoder.encode(signUpReq.getPassword())); 
        newUser.setRole("USER");
        userRepository.save(newUser);
        
        return  ResponseEntity.ok().body("User Signed In");
        
        

        
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody String body) {
        Optional<User> userOpt = Optional.ofNullable(userRepository.findByEmail(body));
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            String token = jwtUtil.generateToken(user.getEmail());
            return ResponseEntity.ok().body("Bearer " + token);
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");

    }
}
    
    
   
    
}
