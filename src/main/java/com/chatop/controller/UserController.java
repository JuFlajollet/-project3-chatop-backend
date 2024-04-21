package com.chatop.controller;

import com.chatop.dto.DBUserDTO;
import com.chatop.service.DBUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "User", description = "User related resources")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private DBUserService dbUserService;

    @Operation(summary = "Get an user by id", description = "Return the user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User successfully retrieved"),
            @ApiResponse(responseCode = "400", description = "Bad request - User not found"),
    })
    @GetMapping(value = "/{userId}", produces = "application/json")
    public ResponseEntity<DBUserDTO> getUser(@PathVariable(value = "userId") Integer userId) {
        DBUserDTO userDTO = dbUserService.findUserById(userId);

        return ResponseEntity.ok().body(userDTO);
    }
}
