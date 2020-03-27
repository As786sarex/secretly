package com.mortazacorp.secretly.controllers;

import com.mortazacorp.secretly.config.RandomUtil;
import com.mortazacorp.secretly.databaseUtil.UserRepository;
import com.mortazacorp.secretly.models.NameModel;
import com.mortazacorp.secretly.models.User;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
public class LoginController {

    private final PasswordEncoder bCryptEncoder;
    private final UserRepository repository;

    public LoginController(PasswordEncoder bCryptEncoder, UserRepository repository) {
        this.bCryptEncoder = bCryptEncoder;
        this.repository = repository;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody NameModel name) {
        if (name.getName() == null) {
            return ResponseEntity.badRequest().build();
        }
        User user = new User();
        User copyUser = new User();
        user.setUserName(RandomUtil.next());
        copyUser.setUserName(user.getUserName());
        copyUser.setPassword(RandomUtil.next());
        copyUser.setName(name.getName());
        user.setName(name.getName());
        user.setPassword(bCryptEncoder.encode(copyUser.getPassword()));
        try {
            repository.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(copyUser);
        } catch (Exception e) {
            throw new DuplicateKeyException("User already exists");
        }
    }
}
