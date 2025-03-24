package com.example.avanade_project.controller;

import com.example.avanade_project.converter.UserConverter;
import com.example.avanade_project.domain.model.User;
import com.example.avanade_project.dtos.UserDTO;
import com.example.avanade_project.dtos.UserPageDTO;
import com.example.avanade_project.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Validated
@RestController
@RequestMapping("/users")
@Tag(name = "Users Controller", description = "RESTful API for managing users.")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @Operation(summary = "Get all users", description = "Retrieve a list of all registered users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation successful")
    })
    public ResponseEntity<UserPageDTO> findAll(@RequestParam("page") @PositiveOrZero int page,
                                              @RequestParam("pageSize") @PositiveOrZero @Max(100) int pageSize) {

        UserPageDTO pageOfUserDto = userService.findAll(page, pageSize);

        return ResponseEntity.status(HttpStatus.OK).body(pageOfUserDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get an user by ID", description = "Retrieve a specific user based on its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation successful"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<UserDTO> findById(@PathVariable @Positive @NotNull Long id) {
        var user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    @Operation(summary = "Create a new user", description = "Create a new user and return the created user's data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created successfully"),
            @ApiResponse(responseCode = "422", description = "Invalid user data provided")
    })
    public ResponseEntity<UserDTO> create(@RequestBody @NotNull UserDTO userToCreate) {

        var userCreated = userService.create(userToCreate);

        UserDTO userDtoCreated = UserConverter.ConvertUserToUserDTO(userCreated);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userCreated.getId())
                .toUri();
        return ResponseEntity.created(location).body(userDtoCreated);
    }

    @PutMapping("/update/{id}/{limit}")
    @Operation(summary = "Updates an user card limit", description = "Updates an user card limit")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Card limit updated successfully"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "422", description = "Invalid data provided")
    })
    public ResponseEntity<UserDTO> updateCardLimit(@PathVariable @Positive @NotNull Long id , @PathVariable @Positive @NotNull BigDecimal limit) {

        User updatedUser = userService.updateCardLimit(id, limit);
        UserDTO updatedUserDto = UserConverter.ConvertUserToUserDTO(updatedUser);
        return new ResponseEntity<>(updatedUserDto, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an user", description = "Delete an existing user based on its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User deleted successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @ResponseStatus(HttpStatus.OK)
    public void deleteUserById(@PathVariable @Positive @NotNull Long id) {

        userService.deleteUserById(id);

    }

}