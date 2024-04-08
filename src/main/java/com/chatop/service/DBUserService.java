package com.chatop.service;

import com.chatop.model.DBUser;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class DBUserService {

    @Autowired
    private com.chatop.repository.DBUserRepository dbUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public DBUser createUser(DBUser user) {
        Optional<DBUser> alreadyExistingUser = dbUserRepository.findByEmail(user.getEmail());

        if(alreadyExistingUser.isPresent()) {
            throw new EntityExistsException("User already exists in DB");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER");
        user.setCreatedAt(LocalDate.now().toString());
        user.setUpdatedAt(LocalDate.now().toString());

        return dbUserRepository.save(user);
    }

    public DBUser findUser(String userEmail) {
        Optional<DBUser> existingUser = dbUserRepository.findByEmail(userEmail);

        if(existingUser.isPresent()) {
            return existingUser.get();
        }else{
            throw new UsernameNotFoundException("User not found");
        }
    }
}
