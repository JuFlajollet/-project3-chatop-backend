package com.chatop.controller;

import com.chatop.model.Message;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messages")
public class MessagesController {

    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<String> sendMessage() {
        //TODO: implement logic
        return ResponseEntity.ok().body("{\"message\": \"Message send with success\"}");
    }
}
