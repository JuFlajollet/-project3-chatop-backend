package com.chatop.controller;

import com.chatop.model.DBUser;
import com.chatop.service.DBUserService;
import com.chatop.service.JWTService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    private static Logger logger = LoggerFactory.getLogger(AuthController.class);

    private AuthenticationManager authenticationManager;
    private JWTService jwtService;
    @Autowired
    private DBUserService dbUserService;

    public AuthController(JWTService jwtService, AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping(value = "/register", produces = "application/json")
    public ResponseEntity<String> registerUser(@RequestBody DBUser user) {
        try {
            logger.info("Trying to create new User {}", user.getEmail());

            dbUserService.createUser(user);

            logger.info("User {} has been successfully created.", user.getEmail());

            UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
            Authentication auth = authenticationManager.authenticate(authReq);

            //TODO: add logic / should generate a jwt token
            String token = jwtService.generateToken(auth);

            return ResponseEntity.ok().body("{\"token\": \""+ token +"\"}");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{}");
        }
    }

    @PostMapping(value = "/login", produces = "application/json")
    public ResponseEntity<String> loginUser(@RequestBody DBUser user) {
        logger.info("Trying to logging User {}", user.getEmail());

        UsernamePasswordAuthenticationToken authReq
                = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        try {
            Authentication auth = authenticationManager.authenticate(authReq);

            logger.info("User {} logged", user.getEmail());
            //TODO: add logic / should generate a jwt token
            String token = jwtService.generateToken(auth);

            return ResponseEntity.ok().body("{\"token\": \""+ token +"\"}");
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"message\": \"error\"}");
        }
    }

    @GetMapping(value = "/me", produces = "application/json")
    public ResponseEntity<String> getUserInfo(Principal user) throws JsonProcessingException {
        //TODO: add logic
        logger.info("User {} acceded /me endpoint", user.getName());

        DBUser dbUser = dbUserService.findUser(user.getName());

        return ResponseEntity.ok().body(new ObjectMapper().writeValueAsString(dbUser));
    }
}
