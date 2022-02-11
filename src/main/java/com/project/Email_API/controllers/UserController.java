package com.project.Email_API.controllers;

import com.project.Email_API.model.AuthRequest;
import com.project.Email_API.model.User;
import com.project.Email_API.service.UserService;
import com.project.Email_API.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    JwtService jwtService;

    @Autowired
    private AuthenticationManagerBuilder authenticationManagerBuilder;

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest authRequest) throws Exception {
       return jwtService.generateToken(authRequest);
    }

    @GetMapping("/allAccounts")
    public List<User> getAllAccounts() {
       return userService.findAllUsers();
    }

    @GetMapping("/account/{id}")
    public Optional<User> getByUsername(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping("/createAccount")
    public ResponseEntity createAccount(@RequestBody User user) {
        return userService.createAccount(user);
    }

    @PutMapping("/changeUsername/{id}")
    public ResponseEntity changeUsername(@PathVariable Long id, @RequestBody User user) {
        return userService.changeUsername(id, user);
    }

    @PutMapping("/changePassword/{id}")
    public ResponseEntity changePassword(@PathVariable Long id, @RequestBody User user) {
       return userService.changePassword(id, user);
    }

    @DeleteMapping("/deleteAccount/{id}")
    public ResponseEntity deleteAccount(@PathVariable Long id) {
        return userService.deleteAccount(id);
    }
}
