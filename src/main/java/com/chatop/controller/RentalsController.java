package com.chatop.controller;

import com.chatop.dto.RentalDTO;
import com.chatop.dto.RentalsDTO;
import com.chatop.dto.ResponseDTO;
import com.chatop.service.RentalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rentals")
public class RentalsController {
    @Autowired
    private RentalsService rentalsService;

    @GetMapping(value = "", produces = "application/json")
    public ResponseEntity<RentalsDTO> getRentals() {
        //TODO: return all rentals with their infos (Need to have a rentals table with rentals: [])
        RentalsDTO rentalsDTO = RentalsDTO.builder().rentals(rentalsService.getRentals()).build();
        return ResponseEntity.ok().body(rentalsDTO);
    }

    @GetMapping(value = "/{rentalId}", produces = "application/json")
    public ResponseEntity<RentalDTO> getRental(@PathVariable(value = "rentalId") Long rentalId) {
        //TODO: return all rentals with their infos
        RentalDTO rentalDTO = rentalsService.getRental(rentalId);
        return ResponseEntity.ok().body(rentalDTO);
    }

    @PostMapping(value = "/{rentalId}", produces = "application/json")
    public ResponseEntity<ResponseDTO> createRental(@PathVariable(value = "rentalId") Long rentalId, @RequestBody RentalDTO newRentalDTO) {
        //TODO: implement logic
        rentalsService.createRental(rentalId, newRentalDTO);
        return ResponseEntity.ok().body(new ResponseDTO("Rental created !"));
    }

    @PutMapping(value = "/{rentalId}", produces = "application/json")
    public ResponseEntity<ResponseDTO> updateRental(@PathVariable(value = "rentalId") Long rentalId, @RequestBody RentalDTO updatedRentalDTO) {
        //TODO: implement logic
        rentalsService.updateRental(rentalId, updatedRentalDTO);
        return ResponseEntity.ok().body(new ResponseDTO("Rental updated !"));
    }
}
