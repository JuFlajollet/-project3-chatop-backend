package com.chatop.service;

import com.chatop.dto.DBUserDTO;
import com.chatop.dto.RentalDTO;
import com.chatop.mapper.RentalMapper;
import com.chatop.model.Rental;
import com.chatop.repository.RentalRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RentalsService {

    private final static Logger logger = LoggerFactory.getLogger(RentalsService.class);

    @Autowired
    private RentalRepository rentalRepository;
    @Autowired
    private RentalMapper rentalMapper;
    @Autowired
    private PictureStorageService pictureStorageService;

    public RentalDTO getRental(Long rentalId) {
        Optional<Rental> dbRental = rentalRepository.findById(rentalId);

        if(dbRental.isPresent()) {
            return rentalMapper.toRentalDTO(dbRental.get());
        }else{
            logger.error("Rental with id {} was not found", rentalId);
            throw new EntityNotFoundException("Rental not found");
        }
    }

    public List<RentalDTO> getRentals() {
        List<Rental> rentals = rentalRepository.findAll();

        return rentals.stream()
                .map(entity -> rentalMapper.toRentalDTO(entity))
                .collect(Collectors.toList());
    }

    public void createRental(DBUserDTO currentUser, MultipartFile picture, RentalDTO newRentalDTO) throws IOException {
        Rental newRental = rentalMapper.toRental(newRentalDTO);

        String formattedDate = formatCurrentDate();

        newRental.setPicture(pictureStorageService.savePicture(picture));
        newRental.setCreatedAt(formattedDate);
        newRental.setUpdatedAt(formattedDate);
        newRental.setOwnerId(currentUser.getId());

        rentalRepository.save(newRental);
    }

    public void updateRental(Long rentalId, RentalDTO updatedRental) {
        Optional<Rental> dbRental = rentalRepository.findById(rentalId);

        if(!dbRental.isPresent()) {
            logger.error("Rental with id {} was not found", rentalId);
            throw new EntityNotFoundException("Rental not found");
        }

        Rental rentalToUpdate = dbRental.get();

        rentalToUpdate.setName(updatedRental.getName());
        rentalToUpdate.setSurface(updatedRental.getSurface());
        rentalToUpdate.setPrice(updatedRental.getPrice());
        rentalToUpdate.setDescription(updatedRental.getDescription());
        rentalToUpdate.setUpdatedAt(formatCurrentDate());

        rentalRepository.save(rentalToUpdate);
    }

    private String formatCurrentDate(){
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        return currentDate.format(dateTimeFormatter);
    }
}
