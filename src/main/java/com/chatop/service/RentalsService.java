package com.chatop.service;

import com.chatop.dto.RentalDTO;
import com.chatop.mapper.RentalMapper;
import com.chatop.model.Rental;
import com.chatop.repository.RentalRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RentalsService {

    @Autowired
    private RentalRepository rentalRepository;
    @Autowired
    private RentalMapper rentalMapper;

    public RentalDTO getRental(Long rentalId) {
        Optional<Rental> dbRental = rentalRepository.findById(rentalId);

        if(dbRental.isPresent()) {
            return rentalMapper.toRentalDTO(dbRental.get());
        }else{
            throw new EntityNotFoundException("Rental not found");
        }
    }

    public List<RentalDTO> getRentals() {
        List<Rental> rentals = rentalRepository.findAll();

        return rentals.stream()
                .map(entity -> rentalMapper.toRentalDTO(entity))
                .collect(Collectors.toList());
    }

    public void createRental(Long rentalId, RentalDTO newRentalDTO) {
        Optional<Rental> dbRental = rentalRepository.findById(rentalId);

        if(dbRental.isPresent()) {
            throw new EntityExistsException("Rental already exists");
        }

        Rental newRental = rentalMapper.toRental(newRentalDTO);

        String formattedDate = formatCurrentDate();

        newRental.setCreatedAt(formattedDate);
        newRental.setUpdatedAt(formattedDate);

        rentalRepository.save(newRental);
    }

    public void updateRental(Long rentalId, RentalDTO updatedRental) {
        Optional<Rental> dbRental = rentalRepository.findById(rentalId);

        if(!dbRental.isPresent()) {
            throw new EntityNotFoundException("Rental not found");
        }

        Rental rentalToUpdate = dbRental.get();

        rentalToUpdate.setName(updatedRental.getName());
        rentalToUpdate.setSurface(updatedRental.getSurface());
        rentalToUpdate.setPrice(updatedRental.getPrice());
        rentalToUpdate.setDescription(updatedRental.getDescription());
        rentalToUpdate.setUpdatedAt(formatCurrentDate());
        //TODO: add logic
        rentalRepository.save(rentalToUpdate);
    }

    private String formatCurrentDate(){
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        return currentDate.format(dateTimeFormatter);
    }
}
