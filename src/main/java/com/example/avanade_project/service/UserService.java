package com.example.avanade_project.service;

import com.example.avanade_project.domain.model.User;
import com.example.avanade_project.dtos.UserDTO;
import com.example.avanade_project.dtos.UserPageDTO;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

public interface UserService {

    UserDTO findById(Long id);

    User create(UserDTO userToCreate);

    UserPageDTO findAll(@PositiveOrZero int page, @Positive @Max(100) int pageSize);

    User updateCardLimit(Long id, BigDecimal limit);

}
