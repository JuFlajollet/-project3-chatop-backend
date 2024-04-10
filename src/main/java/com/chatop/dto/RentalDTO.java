package com.chatop.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RentalDTO {
    private Long id;
    private String name;
    private Integer surface;
    private Integer price;
    //TODO: Check if string is good type for picture url/uri
    private String picture;
    private String description;
    private Long ownerId;
    private String createdAt;
    private String updatedAt;
}
