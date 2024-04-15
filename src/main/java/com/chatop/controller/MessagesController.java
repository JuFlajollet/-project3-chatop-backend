package com.chatop.controller;

import com.chatop.dto.MessageDTO;
import com.chatop.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messages")
public class MessagesController {

    @Autowired
    private MessageService messageService;

    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<String> sendMessage(@RequestBody MessageDTO messageDTO) {
        messageService.createMessage(messageDTO);

        return ResponseEntity.ok().body("{\"message\": \"Message send with success\"}");
    }
}
