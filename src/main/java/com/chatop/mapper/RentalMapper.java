package com.chatop.mapper;

import com.chatop.dto.RentalDTO;
import com.chatop.model.Rental;
import org.springframework.stereotype.Component;

@Component
public class RentalMapper {
    public RentalDTO toRentalDTO(Rental rental) {
        if(rental == null){
            return null;
        }

        return RentalDTO.builder()
                .id(rental.getId())
                .name(rental.getName())
                .surface(rental.getSurface())
                .price(rental.getPrice())
                .picture(rental.getPicture())
                .description(rental.getDescription())
                .ownerId(rental.getOwnerId())
                .createdAt(rental.getCreatedAt())
                .updatedAt(rental.getUpdatedAt())
                .build();
    }

    public Rental toRental(RentalDTO rentalDTO){
        if(rentalDTO == null){
            return null;
        }

        return Rental.builder()
                .name(rentalDTO.getName())
                .surface(rentalDTO.getSurface())
                .price(rentalDTO.getPrice())
                .picture(rentalDTO.getPicture())
                .description(rentalDTO.getDescription())
                .build();
    }
}
