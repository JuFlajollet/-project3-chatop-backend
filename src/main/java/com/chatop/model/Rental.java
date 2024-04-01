package com.chatop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer surface;
    private Integer price;
    //TODO: add picture, is it a string table?
    private String description;
    private Integer ownerId;
    //TODO: add createdAt
    //TODO: add updatedAt

}
