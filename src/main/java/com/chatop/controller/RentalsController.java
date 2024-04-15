package com.chatop.controller;

import com.chatop.dto.DBUserDTO;
import com.chatop.dto.RentalDTO;
import com.chatop.dto.RentalsDTO;
import com.chatop.dto.ResponseDTO;
import com.chatop.service.DBUserService;
import com.chatop.service.RentalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@RestController
@RequestMapping("/rentals")
public class RentalsController {
    @Autowired
    private RentalsService rentalsService;
    @Autowired
    private DBUserService dbUserService;

    @GetMapping(value = "", produces = "application/json")
    public ResponseEntity<RentalsDTO> getRentals() {
        RentalsDTO rentalsDTO = RentalsDTO.builder().rentals(rentalsService.getRentals()).build();

        return ResponseEntity.ok().body(rentalsDTO);
    }

    @GetMapping(value = "/{rentalId}", produces = "application/json")
    public ResponseEntity<RentalDTO> getRental(@PathVariable(value = "rentalId") Long rentalId) {
        RentalDTO rentalDTO = rentalsService.getRental(rentalId);

        return ResponseEntity.ok().body(rentalDTO);
    }

    @PostMapping(value = "", produces = "application/json", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ResponseDTO> createRental(Principal user, @RequestPart(value = "picture") MultipartFile picture, @ModelAttribute RentalDTO newRentalDTO) throws IOException {
        DBUserDTO currentUser = dbUserService.findUserByEmail(user.getName());

        rentalsService.createRental(currentUser, picture, newRentalDTO);

        return ResponseEntity.ok().body(new ResponseDTO("Rental created !"));
    }

    @PutMapping(value = "/{rentalId}", produces = "application/json")
    public ResponseEntity<ResponseDTO> updateRental(@PathVariable(value = "rentalId") Long rentalId, @ModelAttribute RentalDTO updatedRentalDTO) {
        rentalsService.updateRental(rentalId, updatedRentalDTO);

        return ResponseEntity.ok().body(new ResponseDTO("Rental updated !"));
    }
}
