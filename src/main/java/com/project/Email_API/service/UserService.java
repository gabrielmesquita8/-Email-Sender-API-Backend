package com.project.Email_API.service;

import com.project.Email_API.model.User;
import com.project.Email_API.repository.UserRepository;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAllUsers() {
        return repository.findAll();
    }

    public Optional<User> findById(Long id) {
        Optional<User> exist = repository.findById(id);
        if(exist.isPresent()) {
            return repository.findById(id);
        }
        else {
            throw new EntityNotFoundException();
        }
    }

    @Transactional
    public ResponseEntity createAccount(User user) {
        if(user.getFirstname().isEmpty() || user.getLastname().isEmpty()
                || user.getUsername().isEmpty() || user.getPassword().isEmpty()) {
            throw new IllegalArgumentException();
        }
        Optional<User> exist = repository.findByUsername(user.getUsername());
        repository.save(user);
        return new ResponseEntity(user, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity changeUsername(Long id, User user) {
        if(user.getUsername().isEmpty()) {
            throw new IllegalArgumentException();
        }
        Optional<User> before = repository.findById(id);
        User newUser;
        if(before.isPresent()) {
            newUser = before.get();
            newUser.setUsername(user.getUsername());
            repository.save(newUser);
            return new ResponseEntity(newUser, HttpStatus.OK);
        }
        else {
            throw new EntityNotFoundException();
        }
    }

    @Transactional
    public ResponseEntity changePassword(Long id, User user) {
        if(user.getPassword().isEmpty()) {
            throw new IllegalArgumentException();
        }
        Optional<User> before = repository.findById(id);
        User newUser;
        if(before.isPresent()) {
            newUser = before.get();
            newUser.setPassword(user.getPassword());
            repository.save(newUser);
            return new ResponseEntity(newUser, HttpStatus.OK);
        }
        else {
            throw new EntityNotFoundException();
        }
    }

    public ResponseEntity deleteAccount(Long id) {
        Optional<User> before = repository.findById(id);
        if(before.isPresent()) {
            User exclude = before.get();
            repository.delete(exclude);
            return new ResponseEntity(exclude, HttpStatus.OK);
        }
        else {
            throw new EntityNotFoundException();
        }
    }
}
