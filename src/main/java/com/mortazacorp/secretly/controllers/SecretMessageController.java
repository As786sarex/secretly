package com.mortazacorp.secretly.controllers;


import com.mortazacorp.secretly.databaseUtil.SecretMessageRepository;
import com.mortazacorp.secretly.databaseUtil.UserRepository;
import com.mortazacorp.secretly.models.SecretMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v1")
public class SecretMessageController {

    private final SecretMessageRepository messageRepository;
    private final UserRepository userRepository;

    public SecretMessageController(SecretMessageRepository messageRepository,
                                   UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }


    @PostMapping("/sendMessage/{userId}")
    public ResponseEntity sendSecretMessage(@RequestBody SecretMessage message, @PathVariable String userId) {
        if(userRepository.existsByUserName(userId)){
            message.setToUserName(userId);
            final SecretMessage save = messageRepository.save(message);
            return ResponseEntity.status(HttpStatus.CREATED).body(save);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/viewAllMessages")
    public List<SecretMessage> getAllMessages(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return messageRepository.getAllByToUserNameOrderByTimestampDesc(currentPrincipalName);
    }

    @GetMapping("/isUserExists/{userId}")
    public ResponseEntity isUserExist(@PathVariable String userId){
        System.out.println(userId);
        if(userRepository.existsByUserName(userId)){
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/isAuthenticated")
    public ResponseEntity isAuthenticated(){
        return ResponseEntity.ok().build();
    }

}
