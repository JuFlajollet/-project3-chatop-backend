package com.chatop.controller;

import com.chatop.dto.DBUserDTO;
import com.chatop.dto.TokenDTO;
import com.chatop.service.DBUserService;
import com.chatop.service.JWTService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@RestController
@RequestMapping("/auth")
public class AuthController {

    private final static Logger logger = LoggerFactory.getLogger(AuthController.class);

    private AuthenticationManager authenticationManager;
    private JWTService jwtService;
    @Autowired
    private DBUserService dbUserService;

    public AuthController(JWTService jwtService, AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping(value = "/register", produces = "application/json")
    public ResponseEntity<TokenDTO> registerUser(@RequestBody DBUserDTO user) {
        logger.info("Trying to create new User {}", user.getEmail());

        dbUserService.createUser(user);

        logger.info("User {} has been successfully created.", user.getEmail());

        UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        Authentication auth = authenticationManager.authenticate(authReq);

        //TODO: add logic / should generate a jwt token
        String token = jwtService.generateToken(auth);

        return ResponseEntity.ok().body(new TokenDTO(token));
    }

    @PostMapping(value = "/login", produces = "application/json")
    public ResponseEntity<TokenDTO> loginUser(@RequestBody DBUserDTO user) throws BadCredentialsException {
        logger.info("Trying to logging User {}", user.getEmail());

        UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        Authentication auth = authenticationManager.authenticate(authReq);

        logger.info("User {} logged", user.getEmail());
        //TODO: add logic / should generate a jwt token
        String token = jwtService.generateToken(auth);

        return ResponseEntity.ok().body(new TokenDTO(token));

    }

    @GetMapping(value = "/me", produces = "application/json")
    public ResponseEntity<DBUserDTO> getUserInfo(Principal user) {
        //TODO: add logic
        logger.info("User {} acceded /me endpoint", user.getName());

        DBUserDTO dbUserDTO = dbUserService.findUserByEmail(user.getName());

        return ResponseEntity.ok().body(dbUserDTO);
    }
}
