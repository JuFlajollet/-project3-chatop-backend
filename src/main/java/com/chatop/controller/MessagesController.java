package com.chatop.controller;

import com.chatop.dto.MessageDTO;
import com.chatop.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Send a message by mail", description = "Return a success message")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Message successfully sent"),
            @ApiResponse(responseCode = "400", description = "Bad request - There was an issue when sending the message"),
    })
    @PostMapping(value = "", produces = "application/json")
    public ResponseEntity<String> sendMessage(@RequestBody MessageDTO messageDTO) {
        messageService.createMessage(messageDTO);

        return ResponseEntity.ok().body("{\"message\": \"Message send with success\"}");
    }
}
