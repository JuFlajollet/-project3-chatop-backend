package com.chatop.controller;

import com.chatop.dto.DBUserDTO;
import com.chatop.dto.TokenDTO;
import com.chatop.service.DBUserService;
import com.chatop.service.JWTService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Autowired
    private JWTService jwtService;
    @Autowired
    private DBUserService dbUserService;

    public AuthController(JWTService jwtService, AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Operation(summary = "Create a new user", description = "Returns a jwt token for the new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully registered"),
            @ApiResponse(responseCode = "400", description = "Bad request - The user already exists")
    })
    @PostMapping(value = "/register", produces = "application/json")
    public ResponseEntity<TokenDTO> registerUser(@RequestBody DBUserDTO user) {
        logger.info("Trying to create new User {}", user.getEmail());

        dbUserService.createUser(user);

        logger.info("User {} has been successfully created.", user.getEmail());

        UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        Authentication auth = authenticationManager.authenticate(authReq);

        String token = jwtService.generateToken(auth);
        logger.info("JWT Token generated for User {}", user.getEmail());

        return ResponseEntity.ok().body(new TokenDTO(token));
    }

    @Operation(summary = "Login an existing user", description = "Returns a jwt token for the existing user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully connected"),
            @ApiResponse(responseCode = "400", description = "Bad request - The user was not found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Login info are incorrect")
    })
    @PostMapping(value = "/login", produces = "application/json")
    public ResponseEntity<TokenDTO> loginUser(@RequestBody DBUserDTO user) throws BadCredentialsException {
        logger.info("Trying to log User {}", user.getEmail());

        UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        Authentication auth = authenticationManager.authenticate(authReq);

        logger.info("User {} logged", user.getEmail());
        String token = jwtService.generateToken(auth);
        logger.info("JWT Token generated for User {}", user.getEmail());

        return ResponseEntity.ok().body(new TokenDTO(token));

    }

    @Operation(summary = "Get active user info from token", description = "Returns the info of the active user from token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "400", description = "Bad request - The user was not found"),
    })
    @GetMapping(value = "/me", produces = "application/json")
    public ResponseEntity<DBUserDTO> getUserInfo(Principal user) {
        DBUserDTO dbUserDTO = dbUserService.findUserByEmail(user.getName());

        return ResponseEntity.ok().body(dbUserDTO);
    }
}
