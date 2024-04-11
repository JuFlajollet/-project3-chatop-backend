package com.chatop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
