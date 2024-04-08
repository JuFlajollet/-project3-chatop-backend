package com.chatop.service;

import com.chatop.model.Rental;
import com.chatop.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RentalsService {

    @Autowired
    private RentalRepository rentalRepository;

    public List<Rental> getRentals() {
        return rentalRepository.findAll();
    }

    public Rental createRental(Rental rental) {
        rental.setCreatedAt(LocalDate.now());
        rental.setUpdatedAt(LocalDate.now());

        return rentalRepository.save(rental);
    }

    public Rental updateRental(Integer rentalId, Rental updatedRental) {
        Rental rental = rentalRepository.findById(rentalId).get();

        rental.setName(updatedRental.getName());
        rental.setSurface(updatedRental.getSurface());
        rental.setPrice(updatedRental.getPrice());
        rental.setDescription(updatedRental.getDescription());
        rental.setUpdatedAt(LocalDate.now());
        //TODO: add logic
        return rentalRepository.save(rental);
    }
}
