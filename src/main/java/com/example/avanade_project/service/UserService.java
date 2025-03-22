package com.example.avanade_project.service;

import com.example.avanade_project.domain.model.User;

import java.util.List;

public interface UserService {

    User findById(Long id);

    User create(User userToCreate);

    List<User> findAll();

}
