package com.chatop.repository;

import com.chatop.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentalRepository extends JpaRepository<Rental, Integer> {
    public List<Rental> findAll();

    public Rental save(Rental rental);
}
