package com.chatop.controller;

import com.chatop.model.Message;
import com.chatop.model.Rental;
import com.chatop.service.RentalsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rentals")
public class RentalsController {
    @Autowired
    private RentalsService rentalsService;

    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<String> getRentals() throws JsonProcessingException {
        //TODO: return all rentals with their infos
        List<Rental> rentals = rentalsService.getRentals();
        return ResponseEntity.ok().body(new ObjectMapper().writeValueAsString(rentals));
    }

    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<String> createRental(@RequestBody Rental newRental) {
        //TODO: implement logic
        rentalsService.createRental(newRental);
        return ResponseEntity.ok().body("{\"message\": \"Rental created !\"}");
    }

    @PutMapping(value = "/{rentalId}", produces = "application/json")
    public ResponseEntity<String> updateRental(@PathVariable(value = "rentalId") Integer rentalId, @RequestBody Rental updatedRental) {
        //TODO: implement logic
        rentalsService.updateRental(rentalId, updatedRental);
        return ResponseEntity.ok().body("{\"message\": \"Rental updated !\"}");
    }
}
