package com.example.avanade_project.controller;

import com.example.avanade_project.domain.model.User;
import com.example.avanade_project.dtos.UserDTO;
import com.example.avanade_project.service.UserService;
import jakarta.validation.constraints.Max;
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
    public ResponseEntity<List<User>> findAll(@RequestParam("page") @PositiveOrZero int page,
                                              @RequestParam("pageSize") @PositiveOrZero @Max(100) int pageSize) {

        Page<User> pageOfUser = userService.findAll(page, pageSize);
        List<User> listOfUsers = pageOfUser.get().collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(listOfUsers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        var user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody UserDTO userToCreate) {
        var userCreated = userService.create(userToCreate);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userCreated.getId())
                .toUri();
        return ResponseEntity.created(location).body(userCreated);
    }

    @PutMapping("/update/{id}/{limit}")
    public ResponseEntity<User> updateCardLimit(@PathVariable Long id , @PathVariable BigDecimal limit) {
        User updatedUser = userService.updateCardLimit(id, limit);
        return new ResponseEntity<>(updatedUser, HttpStatus.ACCEPTED);
    }

}