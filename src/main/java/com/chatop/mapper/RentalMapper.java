package com.chatop.mapper;

import com.chatop.dto.RentalDTO;
import com.chatop.model.Rental;
import com.chatop.util.DateFormatterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RentalMapper {
    @Autowired
    private DateFormatterUtils dateFormatterUtils;

    public RentalDTO toRentalDTO(Rental rental) {
        if(rental == null){
            return null;
        }

        return RentalDTO.builder()
                .id(rental.getId())
                .name(rental.getName())
                .surface(rental.getSurface())
                .price(rental.getPrice())
                .pictureUrl(rental.getPicture())
                .description(rental.getDescription())
                .ownerId(rental.getOwnerId())
                .createdAt(dateFormatterUtils.formatDateForDisplay(rental.getCreatedAt()))
                .updatedAt(dateFormatterUtils.formatDateForDisplay(rental.getUpdatedAt()))
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
                .picture(rentalDTO.getPictureUrl())
                .description(rentalDTO.getDescription())
                .build();
    }
}
