package com.chatop.controller;

import com.chatop.model.Message;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rentals")
public class RentalsController {
    @GetMapping("/")
    public String getRentals() {
        //TODO: return all rentals with their infos
        return "";
    }

    @PostMapping("/")
    public Message createRental() {
        //TODO: implement logic
        return Message.builder().setMessage("Rental created !").build();
    }

    @PutMapping("/{id}")
    public Message updateRental() {
        //TODO: implement logic
        return Message.builder().setMessage("Rental updated !").build();
    }
}
