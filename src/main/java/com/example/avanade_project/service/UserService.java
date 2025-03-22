package com.example.avanade_project.service;

import com.example.avanade_project.domain.model.User;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.data.domain.Page;

public interface UserService {

    User findById(Long id);

    User create(User userToCreate);

    Page<User> findAll(@PositiveOrZero int page, @Positive @Max(100) int pageSize);

}
