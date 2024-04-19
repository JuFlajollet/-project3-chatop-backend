package com.chatop.service;

import com.chatop.dto.DBUserDTO;
import com.chatop.dto.RentalDTO;
import com.chatop.util.DateFormatterUtils;
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
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RentalsService {

    @Autowired
    private RentalRepository rentalRepository;
    @Autowired
    private DateFormatterUtils dateFormatterUtils;
    @Autowired
    private RentalMapper rentalMapper;
    @Autowired
    private PictureStorageService pictureStorageService;

    private final static Logger logger = LoggerFactory.getLogger(RentalsService.class);

    public RentalDTO getRental(Integer rentalId) {
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

        Timestamp formattedDate = dateFormatterUtils.formatCurrentDateForDB();

        newRental.setPicture(pictureStorageService.storePicture(picture));
        newRental.setCreatedAt(formattedDate);
        newRental.setUpdatedAt(formattedDate);
        newRental.setOwnerId(currentUser.getId());

        rentalRepository.save(newRental);
    }

    public void updateRental(Integer rentalId, RentalDTO updatedRental) {
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
        rentalToUpdate.setUpdatedAt(dateFormatterUtils.formatCurrentDateForDB());

        rentalRepository.save(rentalToUpdate);
    }
}
