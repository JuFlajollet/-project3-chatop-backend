package com.chatop.service;

import com.chatop.dto.DBUserDTO;
import com.chatop.mapper.DBUserMapper;
import com.chatop.model.DBUser;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class DBUserService {
    @Autowired
    private com.chatop.repository.DBUserRepository dbUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private DBUserMapper dbUserMapper;

    public void createUser(DBUserDTO userDTO) {
        DBUser user = dbUserMapper.toDBUser(userDTO);
        Optional<DBUser> dbUser = dbUserRepository.findByEmail(user.getEmail());

        if(dbUser.isPresent()) {
            throw new EntityExistsException("User already exists in DB");
        }

        String formattedDate = formatCurrentDate();

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER");
        user.setCreatedAt(formattedDate);
        user.setUpdatedAt(formattedDate);

        dbUserRepository.save(user);
    }

    public DBUserDTO findUserByEmail(String userEmail) {
        Optional<DBUser> dbUser = dbUserRepository.findByEmail(userEmail);

        if(dbUser.isPresent()) {
            return dbUserMapper.toDBUserDTO(dbUser.get());
        }else{
            throw new UsernameNotFoundException("User not found");
        }
    }

    public DBUserDTO findUserById(long userId) {
        Optional<DBUser> dbUser = dbUserRepository.findById(userId);

        if(dbUser.isPresent()) {
            return dbUserMapper.toDBUserDTO(dbUser.get());
        }else{
            throw new UsernameNotFoundException("User not found");
        }
    }

    private String formatCurrentDate(){
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        return currentDate.format(dateTimeFormatter);
    }
}
