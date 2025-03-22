package com.example.avanade_project.service.impl;

import com.example.avanade_project.converter.UserConverter;
import com.example.avanade_project.domain.repository.UserRepository;
import com.example.avanade_project.dtos.UserDTO;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.example.avanade_project.service.UserService;
import com.example.avanade_project.domain.model.User;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.util.NoSuchElementException;


@Validated
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Page<User> findAll(@PositiveOrZero int page, @Positive @Max(100) int pageSize) {
        return userRepository.findAll(PageRequest.of(page, pageSize));
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public User create(UserDTO userToCreate) {
        if (userRepository.existsByAccountNumber(userToCreate.account().getNumber())) {
            throw new IllegalArgumentException("This Account number already exists!");
        }
        if(userRepository.existsByCardNumber(userToCreate.card().getNumber())) {
        throw new IllegalArgumentException("This Card number already exists!");
    }
        return userRepository.save(UserConverter.ConvertUserDTOtoUser(userToCreate));
    }

    @Override
    public User updateCardLimit(Long id, BigDecimal limit) {
        User userToBeFound = userRepository.findById(id).orElseThrow(NoSuchElementException::new);

        userToBeFound.getCard().setLimit(limit);

        userRepository.save(userToBeFound);

        return userToBeFound;

    }

}