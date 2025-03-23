package com.example.avanade_project.service;

import com.example.avanade_project.domain.model.User;
import com.example.avanade_project.dtos.UserDTO;
import com.example.avanade_project.dtos.UserPageDTO;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

public interface UserService {

    UserDTO findById(@Positive @NotNull Long id);

    User create(@NotNull UserDTO userToCreate);

    UserPageDTO findAll(@PositiveOrZero @NotNull int page,@NotNull @Positive @Max(100) int pageSize);

    User updateCardLimit(@Positive @NotNull Long id, @Positive @NotNull BigDecimal limit);

    void deleteUserById(@Positive @NotNull Long id);

}
