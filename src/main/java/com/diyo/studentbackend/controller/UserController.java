package com.diyo.studentbackend.controller;

import com.diyo.studentbackend.entity.User;
import com.diyo.studentbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@CrossOrigin("http://localhost:4200")
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user){
        this.userRepository.save(user);
        return ResponseEntity.ok("success");
    }

    @PostMapping("/login")
    public ResponseEntity<String> validateUser(@RequestBody User user) {
        Optional<User> userFromDB = this.userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
        if(userFromDB.isPresent()) {
            return ResponseEntity.ok("success");
        } else {
            return ResponseEntity.ok("Invalid email or password");
        }
    }
}
