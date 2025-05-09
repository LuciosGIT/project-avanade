package com.example.avanade_project.service.impl;

import com.example.avanade_project.converter.UserConverter;
import com.example.avanade_project.domain.repository.UserRepository;
import com.example.avanade_project.dtos.UserDTO;
import com.example.avanade_project.dtos.UserPageDTO;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.example.avanade_project.service.UserService;
import com.example.avanade_project.domain.model.User;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;


@Validated
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserPageDTO findAll(@PositiveOrZero @NotNull int page, @NotNull @Positive @Max(100) int pageSize) {
        Page<User> pageOfUsers = userRepository.findAll(PageRequest.of(page, pageSize));
        List<UserDTO> listOfUsersDTO = pageOfUsers.get().map(UserConverter::ConvertUserToUserDTO).collect(Collectors.toList());

        return new UserPageDTO(listOfUsersDTO, pageOfUsers.getTotalElements(), pageOfUsers.getTotalPages());
    }

    @Override
    public UserDTO findById(@Positive @NotNull Long id) {
        return UserConverter.ConvertUserToUserDTO(userRepository.findById(id).orElseThrow(NoSuchElementException::new));
    }

    @Override
    public User create(@NotNull UserDTO userToCreate) {
        if (userRepository.existsByAccountNumber(userToCreate.account().getNumber())) {
            throw new IllegalArgumentException("This Account number already exists!");
        }
        if(userRepository.existsByCardNumber(userToCreate.card().getNumber())) {
        throw new IllegalArgumentException("This Card number already exists!");
    }
        return userRepository.save(UserConverter.ConvertUserDTOtoUser(userToCreate));
    }

    @Override
    public User updateCardLimit(@Positive @NotNull Long id, @Positive @NotNull BigDecimal limit) {
        User userToBeFound = userRepository.findById(id).orElseThrow(NoSuchElementException::new);

        userToBeFound.getCard().setLimit(limit);

        userRepository.save(userToBeFound);

        return userToBeFound;

    }

    @Override
    public void deleteUserById(@Positive @NotNull Long id) {
        userRepository.findById(id).orElseThrow(NoSuchElementException::new);

        userRepository.deleteById(id);

    }

}