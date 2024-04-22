package com.chatop.service;

import com.chatop.dto.DBUserDTO;
import com.chatop.mapper.DBUserMapper;
import com.chatop.model.DBUser;
import com.chatop.repository.DBUserRepository;
import com.chatop.util.DateFormatterUtils;
import jakarta.persistence.EntityExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class DBUserService {
    @Autowired
    private DBUserRepository dbUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private DateFormatterUtils dateFormatterUtils;
    @Autowired
    private DBUserMapper dbUserMapper;

    private final static Logger logger = LoggerFactory.getLogger(DBUserService.class);

    public void createUser(DBUserDTO userDTO) {
        DBUser user = dbUserMapper.toDBUser(userDTO);
        Optional<DBUser> dbUser = dbUserRepository.findByEmail(user.getEmail());

        if(dbUser.isPresent()) {
            logger.error("User {} already exists in DB", user.getEmail());
            throw new EntityExistsException("User already exists in DB");
        }

        Timestamp formattedDate = dateFormatterUtils.formatCurrentDateForDB();

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreatedAt(formattedDate);
        user.setUpdatedAt(formattedDate);

        dbUserRepository.save(user);
    }

    public DBUserDTO findUserByEmail(String userEmail) {
        Optional<DBUser> dbUser = dbUserRepository.findByEmail(userEmail);

        if(dbUser.isPresent()) {
            return dbUserMapper.toDBUserDTO(dbUser.get());
        }else{
            logger.error("User {} not found", userEmail);
            throw new UsernameNotFoundException("User not found");
        }
    }

    public DBUserDTO findUserById(Integer userId) {
        Optional<DBUser> dbUser = dbUserRepository.findById(userId);

        if(dbUser.isPresent()) {
            return dbUserMapper.toDBUserDTO(dbUser.get());
        }else{
            logger.error("User with id {} not found", userId);
            throw new UsernameNotFoundException("User not found");
        }
    }
}
