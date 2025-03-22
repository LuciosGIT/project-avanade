package com.example.avanade_project.service;

import com.example.avanade_project.domain.model.User;
import com.example.avanade_project.dtos.UserDTO;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;

public interface UserService {

    User findById(Long id);

    User create(UserDTO userToCreate);

    Page<User> findAll(@PositiveOrZero int page, @Positive @Max(100) int pageSize);

    User updateCardLimit(Long id, BigDecimal limit);

}
