package com.example.avanade_project.service;

import com.example.avanade_project.domain.model.User;

public interface UserService {

    User findById(Long id);

    User create(User userToCreate);
}
