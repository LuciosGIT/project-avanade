package com.example.avanade_project.controller;

import com.example.avanade_project.converter.UserConverter;
import com.example.avanade_project.domain.model.User;
import com.example.avanade_project.dtos.UserDTO;
import com.example.avanade_project.dtos.UserPageDTO;
import com.example.avanade_project.service.UserService;
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
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<UserPageDTO> findAll(@RequestParam("page") @PositiveOrZero int page,
                                              @RequestParam("pageSize") @PositiveOrZero @Max(100) int pageSize) {

        UserPageDTO pageOfUserDto = userService.findAll(page, pageSize);

        return ResponseEntity.status(HttpStatus.OK).body(pageOfUserDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable @Positive @NotNull Long id) {
        var user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
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
    public ResponseEntity<UserDTO> updateCardLimit(@PathVariable @Positive @NotNull Long id , @PathVariable @Positive @NotNull BigDecimal limit) {

        User updatedUser = userService.updateCardLimit(id, limit);
        UserDTO updatedUserDto = UserConverter.ConvertUserToUserDTO(updatedUser);
        return new ResponseEntity<>(updatedUserDto, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUserById(@PathVariable @Positive @NotNull Long id) {

        userService.deleteUserById(id);

    }

}